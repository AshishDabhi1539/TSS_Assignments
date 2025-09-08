package com.tss.banking.entity;

import java.time.LocalDateTime;

import com.tss.banking.entity.enums.TransactionStatus;
import com.tss.banking.entity.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "transactions", indexes = {
        @Index(name = "idx_transactions_account", columnList = "account_id"),
        @Index(name = "idx_transactions_created_at", columnList = "date"),
        @Index(name = "idx_transactions_idem_key", columnList = "idempotencyKey", unique = true)
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TransactionStatus status;

    private String referenceNumber;

    @Column(length = 100)
    private String idempotencyKey;

    private Double balanceBefore;

    private Double balanceAfter;

    private String failureReason;

    @PrePersist
    void onCreate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
        if (status == null) {
            status = TransactionStatus.PENDING;
        }
    }
}