package com.example.bookclub.payment.repository;

import com.example.bookclub.payment.domain.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    Boolean existsByPaymentId(Long paymentId);
}
