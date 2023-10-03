package com.example.bookclub.common.exception;

public class InvalidValueException extends BusinessException{
    public InvalidValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
