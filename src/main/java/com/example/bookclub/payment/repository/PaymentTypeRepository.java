package com.example.bookclub.payment.repository;

import com.example.bookclub.payment.domain.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
