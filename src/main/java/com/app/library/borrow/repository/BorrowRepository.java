package com.app.library.borrow.repository;

import com.app.library.borrow.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    @Override
    List<Borrow> findAll();

    Optional<Borrow> findBorrowById(Long borrowId);

    @Override
    Borrow save(Borrow borrow);

    @Override
    void delete(Long borrowId);

    void deleteAll();
}
