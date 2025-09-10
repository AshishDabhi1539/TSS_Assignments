package com.tss.banking.entity;

import java.math.BigDecimal;
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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "transactions", indexes = {
        @Index(name = "idx_transactions_account", columnList = "account_id"),
        @Index(name = "idx_transactions_created_at", columnList = "date"),
        @Index(name = "idx_transactions_idem_key", columnList = "idempotencyKey", unique = true),
        @Index(name = "idx_transactions_status_type", columnList = "status,type"),
        @Index(name = "idx_transactions_recipient", columnList = "recipient_account_id")
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_account_id")
    private Account recipientAccount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TransactionType type;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;
    
    @Column(precision = 15, scale = 2)
    private BigDecimal feeAmount;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(length = 512)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TransactionStatus status;

    private String referenceNumber;

    @Column(length = 100)
    private String idempotencyKey;

    @Column(precision = 15, scale = 2)
    private BigDecimal balanceBefore;

    @Column(precision = 15, scale = 2)
    private BigDecimal balanceAfter;

    @Column(length = 512)
    private String failureReason;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
        if (status == null) {
            status = TransactionStatus.PENDING;
        }
        if (feeAmount == null) {
            feeAmount = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}