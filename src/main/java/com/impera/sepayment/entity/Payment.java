package com.impera.sepayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Payment {
    public enum  PaymentStatus {
        PENDING,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int amount;

    String ref;

    String txnId; // Transaction ID from the payment gateway

    @Lob
    @Column(columnDefinition = "TEXT")
    String callBackData; // Data received from the payment gateway callback

    @Enumerated(EnumType.STRING)
    PaymentStatus status = PaymentStatus.PENDING;

    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;
}
