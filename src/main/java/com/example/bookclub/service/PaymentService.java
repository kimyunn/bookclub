package com.example.bookclub.service;

import com.example.bookclub.common.error.exception.BusinessException;
import com.example.bookclub.common.error.exception.ErrorCode;
import com.example.bookclub.domain.*;
import com.example.bookclub.dto.request.PaymentRequestDto;
import com.example.bookclub.dto.response.PaymentResponseDto;
import com.example.bookclub.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;

    public PaymentService(SubscriptionRepository subscriptionRepository, PaymentTypeRepository paymentTypeRepository,
                          PaymentRepository paymentRepository, RefundRepository refundRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentRepository = paymentRepository;
        this.refundRepository = refundRepository;
    }

    public Long payment(PaymentRequestDto requestDto) {
        Subscription subscription = subscriptionRepository.findById(requestDto.getSubscriptionId())
                .orElseThrow(() -> new BusinessException(ErrorCode.SUBSCRIPTION_NOT_FOUND));
        PaymentType paymentType = paymentTypeRepository.findById(requestDto.getPaymentTypeId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PAYMENTTYPE_NOT_FOUND));
        return paymentRepository.save(Payment.createOrder(requestDto.getUserId(), subscription, paymentType)).getId();
    }

    public Page<PaymentResponseDto> getPaymentHistory(Long userId, Pageable pageable) {
        return paymentRepository.findByUserId(userId, pageable).map(payment -> new PaymentResponseDto(payment));
    }

    public void cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new BusinessException(ErrorCode.PAYMENT_NOT_FOUND));
        refundRepository.save(Refund.createRefund(payment));
    }
}
