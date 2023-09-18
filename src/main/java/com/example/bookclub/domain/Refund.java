package com.example.bookclub.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AttributeOverride(name="createdDate", column = @Column(name="cancelledDate"))
@NoArgsConstructor
@Entity
public class Refund extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int amount;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Builder
    public Refund(Long id, int amount, Payment payment) {
        this.id = id;
        this.amount = amount;
        this.payment = payment;
    }

    public static Refund createRefund(Payment payment) {
        return Refund.builder()
                .amount(payment.getSubscription().getPrice())
                .payment(payment)
                .build();
    }
}
