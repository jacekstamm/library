package com.app.book.controller;

import com.app.book.dto.BookDto;
import com.app.book.mapper.BookMapper;
import com.app.book.service.BookService;
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
}
