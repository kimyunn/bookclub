package com.example.bookclub.book.exception;

import com.example.bookclub.common.exception.BusinessException;
import com.example.bookclub.common.exception.ErrorCode;

public class BookDuplicationException extends BusinessException {
    public BookDuplicationException(String message) {
        super(message, ErrorCode.DUPLICATE_BOOK_ISBN);
    }
}
