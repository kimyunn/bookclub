package com.example.bookclub.user.exception;

import com.example.bookclub.common.exception.ErrorCode;
import com.example.bookclub.common.exception.NotFoundException;

public class EmailNotFoundException extends NotFoundException {
    public EmailNotFoundException(String message) {
        super(message, ErrorCode.EMAIL_NOT_FOUND);
    }
}
