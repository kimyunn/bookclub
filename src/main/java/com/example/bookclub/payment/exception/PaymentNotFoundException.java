package com.example.bookclub.payment.exception;

import com.example.bookclub.common.exception.ErrorCode;
import com.example.bookclub.common.exception.NotFoundException;

public class PaymentNotFoundException extends NotFoundException {
    public PaymentNotFoundException(String message) {
        super(message, ErrorCode.PAYMENT_NOT_FOUND);
    }
}
