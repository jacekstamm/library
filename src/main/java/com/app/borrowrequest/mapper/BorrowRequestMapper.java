package com.app.borrowrequest.mapper;

import com.app.borrowrequest.domain.BorrowRequest;
import com.app.borrowrequest.dto.BorrowRequestDto;
import org.springframework.stereotype.Component;

@Component
public class BorrowRequestMapper {

    public BorrowRequest mapToBorrow(final BorrowRequestDto borrowRequestDto) {
        return new BorrowRequest(
                borrowRequestDto.getId(),
                borrowRequestDto.getUserId(),
                borrowRequestDto.getBookId(),
                borrowRequestDto.getBorrowDate(),
                borrowRequestDto.getReturnDate());
    }

    public BorrowRequestDto mapToBorrowDto(final BorrowRequest borrowRequest) {
        return new BorrowRequestDto(
                borrowRequest.getId(),
                borrowRequest.getUserId(),
                borrowRequest.getBookId(),
                borrowRequest.getBorrowDate(),
                borrowRequest.getReturnDate());
    }
}
