package com.app.borrowrequest.service;

import com.app.book.service.BookService;
import com.app.borrowrequest.domain.BorrowRequest;
import com.app.borrowrequest.repository.BorrowRequestRepository;
import com.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowRequestService {

    @Autowired
    BorrowRequestRepository borrowRequestRepository;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;


    public List<BorrowRequest> getAllBorrow() {
        return borrowRequestRepository.findAll();
    }

    public Optional<BorrowRequest> findBorrowById(final Long borrowId) {
        return borrowRequestRepository.findBorrowById(borrowId);
    }

    public BorrowRequest saveBorrow(final BorrowRequest borrowRequest) {
        return borrowRequestRepository.save(borrowRequest);
    }

    public void deleteBorrow(final Long borrowId) {
        borrowRequestRepository.delete(borrowId);
    }
}
