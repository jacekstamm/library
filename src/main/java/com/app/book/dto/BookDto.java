package com.app.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String yearOfPublication;
    private boolean isRented = false;

    public BookDto(String title, String author, String yearOfPublication, boolean isRented) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.isRented = isRented;
    }
}
