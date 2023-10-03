package com.example.bookclub.payment.repository;

import com.example.bookclub.payment.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
