package com.example.bookclub.payment.dto.response;

import com.example.bookclub.payment.entity.Payment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PaymentResponse {
    private Long id;
    private String subscriptionName;
    private LocalDateTime paymentDate;
    private String paymentType;
    private int price;

    public PaymentResponse(Payment payment) {
        this.id = payment.getId();
        this.subscriptionName = payment.getSubscription().getName();
        this.paymentDate = payment.getPaymentDate();
        this.paymentType = payment.getPaymentType().getType();
        this.price = payment.getSubscription().getPrice();
    }
}

