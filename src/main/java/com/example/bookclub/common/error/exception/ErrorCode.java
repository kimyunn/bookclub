package com.example.bookclub.common.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "c001", "입력값이 올바르지 않습니다."),

    // Book
    DUPLICATE_BOOK_ISBN(HttpStatus.CONFLICT, "b001", "이미 등록된 isbn번호입니다."),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND,"b002", "해당 도서를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.status = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }


}