package com.app.book.controller;

import com.app.book.dto.BookDto;
import com.app.book.exception.BookNotFoundException;
import com.app.book.mapper.BookMapper;
import com.app.book.service.BookService;
import com.app.user.dto.UserDto;
import com.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    @GetMapping(value = "getBook")
    public  BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookService.findBookById(bookId).orElseThrow(BookNotFoundException::new));
    }

    @PostMapping(value = "createBook")
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping(value = "updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(bookService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping(value = "borrowBook")
    public void borrowBook(@RequestBody UserDto userDto, @RequestBody BookDto bookDto) {
        if (!bookDto.isRented() && bookService.findBookById(bookDto.getId()).isPresent() && userService.getUser(userDto.getId()).isPresent()) {
            bookDto.setRented(true);
            LOGGER.info("Borrowed book: " + bookDto.getTitle());
        } else {
            LOGGER.info("Book is borrowed already");
        }
    }
}
