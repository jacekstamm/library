package com.app.book.service;

import com.app.book.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void deleteAll() {
        //Given
        bookService.saveBook(new Book("Quo Vadis", "Henryk Sienkiewicz", "1894", false));
        bookService.saveBook(new Book("Lśnienie", "Stephan King", "1990", false));
        bookService.saveBook(new Book("James Bond", "Bond James", "2010", false));
        int bookListBefore = bookService.getAllBooks().size();
        //When
        bookService.deleteAll();
        int bookListAfter = bookService.getAllBooks().size();
        //Then
        assertEquals(3, bookListBefore);
        assertEquals(0, bookListAfter);
    }
}