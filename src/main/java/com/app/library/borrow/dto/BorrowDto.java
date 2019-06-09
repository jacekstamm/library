package com.app.library.borrow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowDto {
    private Long id;
    private Long userId;
    private Long bookId;
}
