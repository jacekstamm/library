package com.app.book.service;

import com.app.book.domain.Book;
import com.app.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(final Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookId) {
        bookRepository.delete(bookId);
    }

    public void deleteAll() {bookRepository.deleteAll();}
}
