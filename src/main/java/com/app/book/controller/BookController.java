package com.app.book.controller;

import com.app.book.dto.BookDto;
import com.app.book.exception.BookNotFoundException;
import com.app.book.mapper.BookMapper;
import com.app.book.service.BookService;
import com.app.library.LibraryProcessor;
import com.app.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private LibraryProcessor libraryProcessor;

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    @GetMapping(value = "getBook")
    public  BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookService.findBookById(bookId).orElseThrow(BookNotFoundException::new));
    }

    @PostMapping(value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
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
        libraryProcessor.borrowBook(bookDto, userDto);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestBody BookDto bookDto) {
        libraryProcessor.returnBook(bookDto);
    }

}
