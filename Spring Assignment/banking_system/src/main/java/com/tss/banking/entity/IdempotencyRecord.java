package com.tss.banking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "idempotency_records", indexes = {
        @Index(name = "uq_idem_key", columnList = "idempotencyKey", unique = true)
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class IdempotencyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String idempotencyKey;

    @Column(length = 128)
    private String requestHash;

    private Long responseEntityId;

    @Column(length = 32)
    private String status; // PENDING, COMPLETED, FAILED

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}


