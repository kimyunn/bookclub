package com.example.bookclub.payment.controller;

import com.example.bookclub.common.exception.dto.ErrorResponse;
import com.example.bookclub.payment.dto.request.PaymentRequest;
import com.example.bookclub.payment.dto.response.PaymentResponse;
import com.example.bookclub.payment.exception.PaymentTypeNotFoundException;
import com.example.bookclub.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "payment", description = "결제 관련 API")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "구독권 결제", description = "해당 구독권을 결제 합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "해당 회원을 찾을 수 없는 경우 \t\n 해당 구독권을 찾을 수 없는 경우 \t\n " +
                    "해당 결제 타입을 찾을 수 없는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<Long> payment(@RequestBody PaymentRequest request) {
        Long id = paymentService.payment(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    @Operation(summary = "결제 내역 조회", description = "해당 회원의 모든 결제 내역을 조회합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "해당 회원을 찾을 수 없는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @GetMapping("/{userId}/history")
    public ResponseEntity<Page<PaymentResponse>> getPaymentHistory(@Parameter(description = "회원 번호") @PathVariable Long userId, Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentService.getPaymentHistory(userId, pageable));
    }

    @Operation(summary = "결제 취소", description = "해당 결제내역을 취소 처리합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "해당 결제내역을 찾을 수 없는 경우", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @DeleteMapping("/{paymentId}/cancel")
    public ResponseEntity cancelPayment(@Parameter(description = "결제 번호") @PathVariable Long paymentId) {
        paymentService.cancelPayment(paymentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}

