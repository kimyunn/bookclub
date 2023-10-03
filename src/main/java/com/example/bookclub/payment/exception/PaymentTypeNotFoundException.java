package com.example.bookclub.payment.exception;

import com.example.bookclub.common.exception.ErrorCode;
import com.example.bookclub.common.exception.NotFoundException;

public class PaymentTypeNotFoundException extends NotFoundException {
    public PaymentTypeNotFoundException(String message) {
        super(message, ErrorCode.PAYMENT_TYPE_NOT_FOUND);
    }
}
