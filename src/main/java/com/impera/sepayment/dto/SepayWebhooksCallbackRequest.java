package com.impera.sepayment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record SepayWebhooksCallbackRequest(
        String id,
        String gateway,
        @JsonProperty("transactionDate")
        LocalDateTime transactionDate,
        @JsonProperty("accountNumber")
        String accountNumber,
        String code,
        String content,
        @JsonProperty("transferType")
        String transferType,
        @JsonProperty("transferAmount")
        Long transferAmount,
        Long accumulated,
        @JsonProperty("subAccount")
        String subAccount,
        @JsonProperty("referenceCode")
        String referenceCode,
        String description
) {
}
