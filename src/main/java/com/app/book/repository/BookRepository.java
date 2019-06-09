package com.app.book.repository;

import com.app.book.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    Optional<Book> findBookById(Long bookId);

    @Override
    Book save(Book book);

    @Override
    void delete(Long bookId);

    void deleteAll();
}
