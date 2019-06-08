package com.app.borrowrequest;

import com.app.borrowrequest.domain.BorrowRequest;

public interface LibraryProcess {
    boolean borrowBook(BorrowRequest borrowRequest);
    boolean returnBook(Long bookID, Long userId);
}
