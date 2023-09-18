package com.example.bookclub.dto.request;

import lombok.Getter;

@Getter
public class PaymentRequestDto {
    private Long userId;
    private Long subscriptionId;
    private Long paymentTypeId;
}
