package com.app.library.borrow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "borrow_id")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "BOOK_ID")
    private Long bookId;

    public Borrow(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
