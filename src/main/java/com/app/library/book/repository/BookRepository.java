package com.app.library.book.repository;

import com.app.library.book.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    Optional<Book> findBookById(Long bookId);

    @Override
    Book save(Book book);

    @Override
    void delete(Long bookId);
}
