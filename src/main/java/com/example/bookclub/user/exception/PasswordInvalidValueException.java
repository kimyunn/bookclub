package com.example.bookclub.user.exception;

import com.example.bookclub.common.exception.ErrorCode;
import com.example.bookclub.common.exception.InvalidValueException;

public class PasswordInvalidValueException extends InvalidValueException {
    public PasswordInvalidValueException(String message) {
        super(message, ErrorCode.INVALID_PASSWORD);
    }
}
