package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfQueryRegexException;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);

    private ApplicationContext context;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("writer"));
            book.setTitle(rs.getString("heading"));
            book.setSize(rs.getInt("scale"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("writer", book.getAuthor());
        parameterSource.addValue("heading", book.getTitle());
        parameterSource.addValue("scale", book.getSize());
        jdbcTemplate.update("INSERT INTO books(writer,heading,scale) VALUES(:writer, :heading, :scale)", parameterSource);
        logger.info("store new book: " + book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookIdToRemove);
        jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
        logger.info("book removed");
        return true;
    }

    @Override
    public void removeItemByRegex(String queryRegex) throws BookShelfQueryRegexException {
        logger.debug(queryRegex);
        if (queryRegex == null || queryRegex.isEmpty()) {
            throw new BookShelfQueryRegexException("emptyQueryRegex");
        }
        var regexParts = queryRegex.toLowerCase().split("=");
        if (regexParts.length == 2) {
            var type = regexParts[0];
            var value = regexParts[1];
            logger.debug(type);
            logger.debug(value);

            if (type.equals("writer") || type.equals("heading") || type.equals("scale")) {
                var parameterSource = new MapSqlParameterSource();

                if (type.equals("scale")) {
                    try {
                        parameterSource.addValue(type, Integer.valueOf(value));
                    } catch (Exception e) {
                        throw new BookShelfQueryRegexException("invalidQueryRegex");
                    }
                } else {
                    parameterSource.addValue(type, value);
                }

                jdbcTemplate.update(String.format("DELETE FROM books WHERE %s = :%s", type, type), parameterSource);
                logger.info(String.format("book removed by %s %s completed", type, value));

            }
        }
        throw new BookShelfQueryRegexException("invalidQueryRegex");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void defaultInit() {
        logger.info("default initialization in the book repository component");
    }

    public void defaultDestroy() {
        logger.info("default destruction in the book repository component");
    }
}
