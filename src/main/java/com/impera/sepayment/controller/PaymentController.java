package com.impera.sepayment.controller;

import com.impera.sepayment.dto.PaymentInitRequest;
import com.impera.sepayment.dto.PaymentResponse;
import com.impera.sepayment.dto.SepayWebhooksCallbackRequest;
import com.impera.sepayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/init")
    public ResponseEntity<Void> init(@RequestBody PaymentInitRequest paymentCreateRequest) {
        return ResponseEntity.created(
                URI.create(paymentService.init(paymentCreateRequest))).build();
    }

    @PostMapping("/callback")
    public ResponseEntity<Void> callback(@RequestBody SepayWebhooksCallbackRequest paymentCreateRequest) {
        paymentService.handleWebhookCallback(paymentCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ref}")
    public ResponseEntity<PaymentResponse> getPaymentByRef(@PathVariable String ref) {
        return ResponseEntity.ok(paymentService.getPaymentByRef(ref));
    }
}
