package com.example.bookclub.payment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaymentRequest {
    private Long userId;
    private Long subscriptionId;
    private Long paymentTypeId;

    public PaymentRequest() {
    }

    @Builder
    public PaymentRequest(Long userId, Long subscriptionId, Long paymentTypeId) {
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.paymentTypeId = paymentTypeId;
    }
}
