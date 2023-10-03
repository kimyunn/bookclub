package com.example.bookclub.common.exception;

public class NotFoundException extends BusinessException{
    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
