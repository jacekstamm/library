package com.app.library.book.controller;

import com.app.library.book.dto.BookDto;
import com.app.library.book.mapper.BookMapper;
import com.app.library.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    @GetMapping(value = "getBook")
    public  BookDto getBook(Long bookId) {
        return new BookDto();
    }

    @PostMapping(value = "createBook")
    public void createBook() {

    }

    @PutMapping(value = "updateBook")
    public BookDto updateBook(Long bookId) {
        return new BookDto();
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(Long bookId) {

    }
}
