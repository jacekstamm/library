package com.app.library.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String yearOfPublication;
    private boolean isRented;
}
