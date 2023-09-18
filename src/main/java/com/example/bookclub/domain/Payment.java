package com.example.bookclub.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;


@Slf4j
@Getter
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "subscription_Id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Builder
    public Payment(Long id, Long userId, Subscription subscription,
                   PaymentType paymentType, LocalDateTime paymentDate,
                   LocalDateTime expiryDate) {
        this.id = id;
        this.userId = userId;
        this.subscription = subscription;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.expiryDate = expiryDate;
    }

    public static Payment createOrder(Long userId, Subscription subscription, PaymentType paymentType) {
        LocalDateTime now = LocalDateTime.now();
        log.info("durationDate={}", subscription.getDurationDate());
        LocalDateTime expiryDate = now.minusDays(subscription.getDurationDate());
        log.info("expiryDate={}", expiryDate);

        return Payment.builder()
                .userId(userId)
                .subscription(subscription)
                .paymentType(paymentType)
                .paymentDate(now)
                .expiryDate(expiryDate)
                .build();
    }
}

