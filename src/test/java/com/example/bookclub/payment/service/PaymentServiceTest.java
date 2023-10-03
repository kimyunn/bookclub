package com.example.bookclub.payment.service;

import com.example.bookclub.common.error.exception.BusinessException;
import com.example.bookclub.payment.domain.Payment;
import com.example.bookclub.payment.dto.request.PaymentRequestDto;
import com.example.bookclub.payment.repository.PaymentRepository;
import com.example.bookclub.payment.repository.RefundRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    RefundRepository refundRepository;

    @Test
    @DisplayName("유저ID, 구독권ID, 결제타입ID를 통해 구독권 결제를 할 수 있다.")
    public void payment() {
        //given
        Long userId = 3L;
        Long subscriptionId = 1L;
        Long paymentType = 1L;
        PaymentRequestDto requestDto = PaymentRequestDto.builder()
                .userId(userId)
                .subscriptionId(subscriptionId)
                .paymentTypeId(paymentType)
                .build();

        Long paymentId = paymentService.payment(requestDto);

        //when
        Payment findPayment = paymentRepository.findById(paymentId).get();

        //then
        assertThat(findPayment.getUserId()).isEqualTo(userId);
        assertThat(findPayment.getSubscription().getId()).isEqualTo(subscriptionId);
        assertThat(findPayment.getPaymentType().getId()).isEqualTo(paymentType);
    }

    @Test
    @DisplayName("존재하지 않는 구독권타입으로 결제를 요청할 경우 예외가 발생한다.")
    public void paymentFail() {
        //given
        Long userId = 3L;
        Long subscriptionId = 7L;
        Long paymentType = 1L;
        PaymentRequestDto requestDto = PaymentRequestDto.builder()
                .userId(userId)
                .subscriptionId(subscriptionId)
                .paymentTypeId(paymentType)
                .build();

        //when, then
        assertThatThrownBy(() -> paymentService.payment(requestDto))
                .isInstanceOf(BusinessException.class)
                .hasMessage("해당 구독권을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("결제ID를 통해 결제를 취소할 수 있다.")
    public void cancelPayment() {
        //given
        Long paymentId = 2L;

        //when
        paymentService.cancelPayment(paymentId);

        //then
        boolean refundExists = refundRepository.existsByPaymentId(paymentId);
        assertThat(refundExists).isTrue();
    }
}
