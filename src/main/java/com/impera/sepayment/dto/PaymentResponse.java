package com.impera.sepayment.dto;

import java.time.LocalDateTime;

public record PaymentResponse (
    int id,
    int amount,
    String orderId,
    String userId,
    String txnId,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
