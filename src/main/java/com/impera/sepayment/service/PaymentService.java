package com.impera.sepayment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.impera.sepayment.dto.PaymentInitRequest;
import com.impera.sepayment.dto.PaymentResponse;
import com.impera.sepayment.dto.SepayWebhooksCallbackRequest;
import com.impera.sepayment.entity.Payment;
import com.impera.sepayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;

    public String init(PaymentInitRequest request) {
        var payment = paymentRepository.save(
                Payment.builder()
                        .amount(request.amount())
                        .ref(request.ref())
                        .build()
        );

        return "/api/v1/payments/" + payment.getRef();
    }

    public void handleWebhookCallback(SepayWebhooksCallbackRequest request) {
        String ref = extractRefFromPaymentContent(request.content());

        if (ref == null) {
            throw new RuntimeException("REF not found in content: " + request.content());
        }

        var payment = paymentRepository.findByRef(ref)
                .orElseThrow(() -> new RuntimeException("Payment not found with ref: " + ref));

        payment.setTxnId(request.id());
        try {
            payment.setCallBackData(objectMapper.writeValueAsString(request));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize callback data", e);
        }
        payment.setStatus(Payment.PaymentStatus.COMPLETED);
        paymentRepository.save(payment);
    }

    private String extractRefFromPaymentContent(String content) {
        // Tìm REF theo format: REF + khoảng trắng + số
        Pattern pattern = Pattern.compile("REF\\s*(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1); // Lấy phần số sau REF
        }
        return null; // Không tìm thấy

    }

    public PaymentResponse getPaymentByRef(String ref) {
        return paymentRepository.findByRef(ref)
                .map(payment -> new PaymentResponse(
                        payment.getId(),
                        payment.getAmount(),
                        payment.getRef(),
                        payment.getTxnId(),
                        payment.getStatus().toString(),
                        payment.getCreatedAt(),
                        payment.getUpdatedAt()
                ))
                .orElseThrow(() -> new RuntimeException("Payment not found with ref: " + ref));
    }
}
