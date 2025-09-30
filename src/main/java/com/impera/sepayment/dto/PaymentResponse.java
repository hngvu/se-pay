package com.impera.sepayment.dto;

import java.time.LocalDateTime;

public record PaymentResponse (
    int id,
    int amount,
    String ref,
    String txnId,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
