package com.app.book.mapper;

import com.app.book.domain.Book;
import com.app.book.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYearOfPublication(),
                bookDto.isRented());
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYearOfPublication(),
                book.isRented());
    }

    public List<Book> mapToBookList(final List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(bookDto -> new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYearOfPublication(), bookDto.isRented()))
                .collect(Collectors.toList());
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(book -> new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getYearOfPublication(), book.isRented()))
                .collect(Collectors.toList());
    }
}
