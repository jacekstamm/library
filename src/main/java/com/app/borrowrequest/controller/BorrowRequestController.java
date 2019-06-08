package com.app.borrowrequest.controller;

import com.app.borrowrequest.LibraryProcessor;
import com.app.borrowrequest.dto.BorrowRequestDto;
import com.app.borrowrequest.mapper.BorrowRequestMapper;
import com.app.borrowrequest.service.BorrowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/borrow")
public class BorrowRequestController {

    @Autowired
    private BorrowRequestMapper borrowRequestMapper;
    @Autowired
    private BorrowRequestService borrowRequestService;
    @Autowired
    private LibraryProcessor libraryProcessor;

    @PostMapping(value = "borrowBook")
    public void borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        if (libraryProcessor.borrowBook(borrowRequestMapper.mapToBorrow(borrowRequestDto))) {
            borrowRequestService.saveBorrow(borrowRequestMapper.mapToBorrow(borrowRequestDto));
        }
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestBody BorrowRequestDto borrowRequestDto) {

    }
}
