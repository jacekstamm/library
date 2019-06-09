package com.app.library.borrow.domain;

import com.app.book.domain.Book;
import com.app.user.domain.User;
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
    private User user;

    @Column(name = "BOOK_ID")
    private Book book;

}
