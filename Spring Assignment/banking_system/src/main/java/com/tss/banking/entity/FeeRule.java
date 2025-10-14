package com.tss.banking.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
=======
import jakarta.persistence.*;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "fee_rules", indexes = {
<<<<<<< HEAD
        @Index(name = "idx_fee_type", columnList = "feeType,effectiveFrom")
=======
        @Index(name = "idx_fee_transaction_type", columnList = "transactionType,isActive")
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class FeeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false, length = 64)
    private String feeType; // e.g., MIN_BALANCE_FEE, INTER_BRANCH_TRANSFER_FEE

    @Column(precision = 15, scale = 2)
    private BigDecimal fixedAmount;

    @Column(precision = 5, scale = 4)
    private BigDecimal percentAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal threshold;
=======
    @Column(nullable = false, length = 32)
    private String transactionType; // TRANSFER, DEBIT, CREDIT, etc.

    @Column(nullable = false, length = 16)
    private String feeType; // FLAT, PERCENTAGE

    @Column(precision = 15, scale = 2)
    private BigDecimal feeAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal minAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal maxAmount;

    @Column(nullable = false)
    private Boolean isActive = true;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;
}


