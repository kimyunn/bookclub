package com.example.bookclub.library.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LibraryRequest {
    // userId, bookId
    private Long userId;
    private Long bookId;

    @Builder
    public LibraryRequest(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
