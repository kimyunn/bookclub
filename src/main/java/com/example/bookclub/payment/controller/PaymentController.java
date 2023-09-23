package com.example.bookclub.payment.controller;

import com.example.bookclub.payment.dto.request.PaymentRequestDto;
import com.example.bookclub.payment.dto.response.PaymentResponseDto;
import com.example.bookclub.payment.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Long> payment(@RequestBody PaymentRequestDto requestDto) {
        Long id = paymentService.payment(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    @GetMapping("/{userId}/history")
    public ResponseEntity<Page<PaymentResponseDto>> getPaymentHistory(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentService.getPaymentHistory(userId, pageable));
    }

    @DeleteMapping("/{paymentId}/cancel")
    public ResponseEntity cancelPayment(@PathVariable Long paymentId) {
        paymentService.cancelPayment(paymentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}

