package com.app.borrowrequest;

import com.app.book.service.BookService;
import com.app.borrowrequest.domain.BorrowRequest;
import com.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryProcessor implements LibraryProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryProcessor.class);

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Override
    public boolean borrowBook(BorrowRequest borrowRequest) {
        if (bookService.findBookById(borrowRequest.getBookId()).isPresent() || userService.getUser(borrowRequest.getUserId()).isPresent() || !bookService.findBookById(borrowRequest.getBookId()).get().isRented()) {
            if (borrowRequest.getBorrowDate().before(borrowRequest.getReturnDate())) {
                bookService.findBookById(borrowRequest.getBookId()).get().setRented(true);
                LOGGER.info("Borrowing process accepted");
                return true;
            } else {
                LOGGER.info("Borrowed date must be before return date");
                return false;
            }
        } else {
            LOGGER.info("Wrong bookId or userId. Check you input information.");
            return false;
        }
    }

    @Override
    public boolean returnBook(Long bookId, Long userId) {
        if (bookService.findBookById(bookId).get().isRented() || userService.getUser(userId).isPresent() || bookService.findBookById(bookId).get().isRented()) {
            bookService.findBookById(bookId).get().setRented(false);
            LOGGER.info("Returning process accepted.");
            return true;
        }
        LOGGER.info("Returning process denied.");
        return false;
    }
}
