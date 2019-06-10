package com.app.book.controller;

import ch.qos.logback.classic.spi.LoggerRemoteView;
import com.app.book.dto.BookDto;
import com.app.book.exception.BookNotFoundException;
import com.app.book.mapper.BookMapper;
import com.app.book.service.BookService;
import com.app.library.LibraryProcessor;
import com.app.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private LibraryProcessor libraryProcessor;

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        LOGGER.info("Found List of books.");
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    @GetMapping(value = "getBook")
    public  BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        LOGGER.info("Book ID: " + bookId + " found.");
        return bookMapper.mapToBookDto(bookService.findBookById(bookId).orElseThrow(BookNotFoundException::new));
    }

    @PostMapping(value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        LOGGER.info("New Book created with ID: " + bookDto.getId());
        bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping(value = "updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        LOGGER.info("Book information changed. Book ID: " + bookDto.getId());
        return bookMapper.mapToBookDto(bookService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
        LOGGER.info("Book ID: " + bookId + " deleted.");
        bookService.deleteBook(bookId);
    }

    @PutMapping(value = "borrowBook")
    public boolean borrowBook(@RequestBody UserDto userDto, @RequestBody BookDto bookDto) {
        return libraryProcessor.borrowBook(bookDto, userDto);
    }

    @PutMapping(value = "returnBook")
    public boolean returnBook(@RequestBody BookDto bookDto) {
        return libraryProcessor.returnBook(bookDto);
    }
}
