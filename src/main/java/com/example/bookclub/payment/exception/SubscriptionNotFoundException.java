package com.example.bookclub.payment.exception;

import com.example.bookclub.common.exception.ErrorCode;
import com.example.bookclub.common.exception.NotFoundException;

public class SubscriptionNotFoundException extends NotFoundException {
    public SubscriptionNotFoundException(String message) {
        super(message, ErrorCode.SUBSCRIPTION_NOT_FOUND);
    }
}
