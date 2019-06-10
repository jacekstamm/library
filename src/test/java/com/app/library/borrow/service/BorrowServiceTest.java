package com.app.library.borrow.service;

import com.app.library.borrow.domain.Borrow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowServiceTest {

    @Autowired
    private BorrowService borrowService;

    @Test
    public void getAllBorrows() {
        //Given
        borrowService.saveBorrow(new Borrow(1L, 2L));
        borrowService.saveBorrow(new Borrow(3L, 4L));
        //When
        List<Borrow> resultList = borrowService.getAllBorrows();
        //Then
        assertEquals(2, resultList.size());
        //CleanUp
        borrowService.deleteAll();
    }

    @Test
    public void findBorrowById() {
        //Given
        borrowService.saveBorrow(new Borrow(1L, 2L));
        Long borrowId = borrowService.getAllBorrows().get(0).getId();
        //When
        Optional<Borrow> result = borrowService.findBorrowById(borrowId);
        //Then
        assertNotNull(result);
        //CleanUp
        borrowService.deleteAll();
    }

    @Test
    public void deleteBorrow() {
        //Given
        borrowService.saveBorrow(new Borrow(1L, 2L));
        Long borrowId = borrowService.getAllBorrows().get(0).getId();
        int berofe = borrowService.getAllBorrows().size();
        //When
        borrowService.deleteBorrow(borrowId);
        int after = borrowService.getAllBorrows().size();
        //Then
        assertEquals(1, berofe);
        assertEquals(0, after);
    }
}