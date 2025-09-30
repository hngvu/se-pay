package com.impera.sepayment.dto;

public record PaymentInitRequest(
    int amount,
    String ref,
) {
}
