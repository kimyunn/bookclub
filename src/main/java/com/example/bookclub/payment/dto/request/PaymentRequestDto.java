package com.example.bookclub.payment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaymentRequestDto {
    private Long userId;
    private Long subscriptionId;
    private Long paymentTypeId;

    public PaymentRequestDto() {
    }

    @Builder
    public PaymentRequestDto(Long userId, Long subscriptionId, Long paymentTypeId) {
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.paymentTypeId = paymentTypeId;
    }
}
