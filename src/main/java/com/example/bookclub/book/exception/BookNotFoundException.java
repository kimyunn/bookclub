package com.example.bookclub.book.exception;

import com.example.bookclub.common.exception.BusinessException;
import com.example.bookclub.common.exception.ErrorCode;

public class BookNotFoundException extends BusinessException {
    public BookNotFoundException(String message) {
        super(message, ErrorCode.BOOK_NOT_FOUND);
    }
}
