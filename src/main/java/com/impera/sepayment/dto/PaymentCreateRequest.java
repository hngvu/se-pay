package com.impera.sepayment.dto;

import lombok.experimental.FieldDefaults;

public record PaymentCreateRequest(
    int amount,
    String orderId,
    String userId,
    String returnUrl
) {
}
