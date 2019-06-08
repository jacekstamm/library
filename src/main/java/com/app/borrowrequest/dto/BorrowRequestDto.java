package com.app.borrowrequest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowRequestDto {

    private Long id;
    private Long userId;
    private Long bookId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
}
