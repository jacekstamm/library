package com.app.library.borrow.service;

import com.app.library.borrow.domain.Borrow;
import com.app.library.borrow.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> findBorrowById(final Long borrowId) {
        return borrowRepository.findBorrowById(borrowId);
    }

    public Borrow saveBorrow(final Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public void deleteBorrow(final Long borrowId) {
        borrowRepository.delete(borrowId);
    }

    public void deleteAll() {
        borrowRepository.deleteAll();
    }
}
