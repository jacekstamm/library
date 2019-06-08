package com.app.borrowrequest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "borrows")
public class BorrowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "borrowDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowDate;

    @Column(name = "returnDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    public BorrowRequest(Long userId, Long bookId, Date borrowDate, Date returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
