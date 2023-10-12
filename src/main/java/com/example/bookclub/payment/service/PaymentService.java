package com.example.bookclub.payment.service;

import com.example.bookclub.payment.entity.Payment;
import com.example.bookclub.payment.entity.PaymentType;
import com.example.bookclub.payment.entity.Refund;
import com.example.bookclub.payment.entity.Subscription;
import com.example.bookclub.payment.dto.request.PaymentRequest;
import com.example.bookclub.payment.dto.response.PaymentResponse;
import com.example.bookclub.payment.exception.PaymentNotFoundException;
import com.example.bookclub.payment.exception.PaymentTypeNotFoundException;
import com.example.bookclub.payment.exception.SubscriptionNotFoundException;
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

    public Long payment(PaymentRequest request) {
        log.info("payment request userId: {}, subscriptionId: {}, paymentTypeId: {}",request.getUserId(), request.getSubscriptionId(), request.getPaymentTypeId() );
        //Subscription subscription = subscriptionRepository.findById(request.getSubscriptionId()).orElseThrow(() -> new BusinessException(ErrorCode.SUBSCRIPTION_NOT_FOUND));
        //PaymentType paymentType = paymentTypeRepository.findById(request.getPaymentTypeId()).orElseThrow(() -> new BusinessException(ErrorCode.PAYMENT_TYPE_NOT_FOUND));
        Subscription subscription = subscriptionRepository.findById(request.getSubscriptionId()).orElseThrow(() -> new SubscriptionNotFoundException(String.format("요청한 구독권이 존재하지 않습니다. id: %d", request.getSubscriptionId())));
        PaymentType paymentType = paymentTypeRepository.findById(request.getPaymentTypeId()).orElseThrow(() -> new PaymentTypeNotFoundException(String.format("요청한 결제타입이 존재하지 않습니다. id: %d", request.getPaymentTypeId())));

        return paymentRepository.save(Payment.createOrder(request.getUserId(), subscription, paymentType)).getId();
    }

    public Page<PaymentResponse> getPaymentHistory(Long userId, Pageable pageable) {
        log.info("paymentHistory request userId: {}", userId);
        return paymentRepository.findByUserId(userId, pageable).map(payment -> new PaymentResponse(payment));
    }

    public void cancelPayment(Long paymentId) {
        log.info("payment cancel request paymentId: {}", paymentId);
//        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new BusinessException(ErrorCode.PAYMENT_NOT_FOUND));
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(String.format("해당 결제내역이 존재하지 않습니다. id : %d", paymentId)));
        refundRepository.save(Refund.createRefund(payment));
    }
}
