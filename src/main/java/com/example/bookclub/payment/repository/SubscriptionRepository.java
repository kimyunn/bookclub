package com.example.bookclub.payment.repository;

import com.example.bookclub.payment.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}

