package ru.sadykoff.skillbox.app.services;

import org.apache.log4j.Logger;
import ru.sadykoff.skillbox.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("new book store: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("book removed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        logger.debug(queryRegex);

        var regexParts = queryRegex.toLowerCase().split("=");
        if (regexParts.length == 2) {
            var view = regexParts[0];
            var meaning = regexParts[1];
            logger.debug(view);
            logger.debug(meaning);

            if (view.equals("writer") || view.equals("heading") || view.equals("scale")) {
                for (Book book : retreiveAll()) {

                    if (view.equals("writer") && book.getAuthor().equals(meaning)) {
                        logger.info(String.format("delete a book by writer %s completed: %s", meaning, book));
                        return repo.remove(book);
                    }
                    if (view.equals("heading") && book.getTitle().equals(meaning)) {
                        logger.info(String.format("delete a book by heading %s completed: %s", meaning, book));
                        return repo.remove(book);
                    }
                    try {
                        if (view.equals("scale") && book.getSize() == Integer.parseInt(meaning)) {
                            logger.info(String.format("delete a book by scale %s completed: %s", meaning, book));
                            return repo.remove(book);
                        }
                    } catch (Exception e) {
                        logger.error(e);
                    }

                }
            }
        }

        return false;
    }
}
