package com.example.bookclub.payment.service;

import com.example.bookclub.common.error.exception.BusinessException;
import com.example.bookclub.common.error.exception.ErrorCode;
import com.example.bookclub.payment.domain.Payment;
import com.example.bookclub.payment.domain.PaymentType;
import com.example.bookclub.payment.domain.Refund;
import com.example.bookclub.payment.domain.Subscription;
import com.example.bookclub.payment.dto.request.PaymentRequestDto;
import com.example.bookclub.payment.dto.response.PaymentResponseDto;
import com.example.bookclub.payment.repository.PaymentRepository;
import com.example.bookclub.payment.repository.PaymentTypeRepository;
import com.example.bookclub.payment.repository.RefundRepository;
import com.example.bookclub.payment.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.info("payment request userId: {}, subscriptionId: {}, paymentTypeId: {}",requestDto.getUserId(), requestDto.getSubscriptionId(), requestDto.getPaymentTypeId() );
        Subscription subscription = subscriptionRepository.findById(requestDto.getSubscriptionId()).orElseThrow(() -> new BusinessException(ErrorCode.SUBSCRIPTION_NOT_FOUND));
        PaymentType paymentType = paymentTypeRepository.findById(requestDto.getPaymentTypeId()).orElseThrow(() -> new BusinessException(ErrorCode.PAYMENTTYPE_NOT_FOUND));
        return paymentRepository.save(Payment.createOrder(requestDto.getUserId(), subscription, paymentType)).getId();
    }

    public Page<PaymentResponseDto> getPaymentHistory(Long userId, Pageable pageable) {
        log.info("paymentHistory request userId: {}", userId);
        return paymentRepository.findByUserId(userId, pageable).map(payment -> new PaymentResponseDto(payment));
    }

    public void cancelPayment(Long paymentId) {
        log.info("payment cancel request paymentId: {}", paymentId);
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new BusinessException(ErrorCode.PAYMENT_NOT_FOUND));
        refundRepository.save(Refund.createRefund(payment));
    }
}
