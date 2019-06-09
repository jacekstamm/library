package com.app.library;

import com.app.book.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LibraryProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryProcessor.class);

    public boolean borrowBook(Book book) {
        if (!book.isRented()) {
            book.setRented(true);
            LOGGER.info("Borrowed book: " + book.getTitle());
            return true;
        } else {
            LOGGER.info("Book is borrowed already");
            return false;
        }
    }

    public boolean returnBook(Book book) {
        if (book.isRented()) {
            book.setRented(false);
            LOGGER.info("Returning book: " + book.getTitle());
            return true;
        } else {
            LOGGER.info("Book is already returned");
            return false;
        }
    }
}
