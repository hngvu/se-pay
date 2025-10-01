package com.impera.sepayment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record SepayWebhooksCallbackRequest(
        String id,
        String gateway,
        @JsonProperty("transactionDate")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
