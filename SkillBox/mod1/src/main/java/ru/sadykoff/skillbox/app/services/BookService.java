package ru.sadykoff.skillbox.app.services;

import ru.sadykoff.skillbox.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public void saveBook(Book book) {
        var writerValid =  book.getAuthor() != null && !book.getAuthor().isEmpty();
        var headingValid =  book.getTitle() != null && !book.getTitle().isEmpty();
        var scaleValid =  book.getSize() != null;

        if (writerValid || headingValid || scaleValid)
            bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public boolean removeBookByRegex(String queryRegex) {
        return bookRepo.removeItemByRegex(queryRegex);
    }
}
