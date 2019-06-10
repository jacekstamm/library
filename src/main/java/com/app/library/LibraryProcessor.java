package com.app.library;

import com.app.book.dto.BookDto;
import com.app.book.mapper.BookMapper;
import com.app.book.service.BookService;
import com.app.library.borrow.domain.Borrow;
import com.app.library.borrow.service.BorrowService;
import com.app.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class LibraryProcessor {

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryProcessor.class);

    public boolean borrowBook(BookDto book, UserDto user) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!book.isRented()) {
            book.setRented(true);
            bookService.saveBook(bookMapper.mapToBook(book));
            Borrow createdBorrow = borrowService.saveBorrow(new Borrow(user.getId(), book.getId()));
            LOGGER.info("User ID: " + user.getId() + " borrowed book: " + book.getTitle() + " | Borrow ID: " + createdBorrow.getId() + " | Time: " + LocalDateTime.now().format(timeFormat));
            return true;
        } else {
            LOGGER.info("Book is borrowed already. Please choose other book.");
            return false;
        }
    }

    public boolean returnBook(BookDto book) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (book.isRented()) {
            book.setRented(false);
            bookService.saveBook(bookMapper.mapToBook(book));
            LOGGER.info("Returning book: " + book.getTitle() + " with ID: " + book.getId() + " | Time: " + LocalDateTime.now().format(timeFormat));
            return true;
        } else {
            LOGGER.info("Book is already returned.");
            return false;
        }
    }
}
