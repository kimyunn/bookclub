package com.example.bookclub.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "c001", "입력값이 올바르지 않습니다."),

    // Book
    DUPLICATE_BOOK_ISBN(HttpStatus.CONFLICT, "b001", "이미 등록된 isbn번호입니다."),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND,"b002", "해당 도서를 찾을 수 없습니다."),

    // Payment
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "p001", "결제 내역을 찾을 수 없습니다."),
    SUBSCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "p002", "해당 구독권을 찾을 수 없습니다."),
    PAYMENT_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "p003", "해당 결제타입을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.status = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}

