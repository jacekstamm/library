package com.app.borrowrequest.repository;

import com.app.borrowrequest.domain.BorrowRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRequestRepository extends CrudRepository<BorrowRequest, Long> {

    @Override
    List<BorrowRequest> findAll();

    Optional<BorrowRequest> findBorrowById(Long borrowId);

    @Override
    BorrowRequest save(BorrowRequest borrowRequest);

    @Override
    void delete(Long borrowId);
}
