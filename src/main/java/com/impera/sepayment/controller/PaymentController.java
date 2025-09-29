package com.impera.sepayment.controller;

import com.impera.sepayment.dto.PaymentCreateRequest;
import com.impera.sepayment.entity.Payment;
import com.impera.sepayment.repository.PaymentRepository;
import com.impera.sepayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Payment> create(@RequestBody PaymentCreateRequest paymentCreateRequest) {}
}
