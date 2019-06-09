package com.app.library;

import com.app.book.dto.BookDto;
import com.app.library.borrow.domain.Borrow;
import com.app.library.borrow.service.BorrowService;
import com.app.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryProcessor {

    @Autowired
    private BorrowService borrowService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryProcessor.class);

    public boolean borrowBook(BookDto book, UserDto user) {
        if (!book.isRented()) {
            book.setRented(true);
            borrowService.saveBorrow(new Borrow(user.getId(), book.getId()));
            LOGGER.info("Borrowed book: " + book.getTitle());
            return true;
        } else {
            LOGGER.info("Book is borrowed already");
            return false;
        }
    }

    public boolean returnBook(BookDto book) {
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
