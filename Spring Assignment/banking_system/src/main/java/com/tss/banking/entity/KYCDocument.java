package com.tss.banking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "kyc_documents", indexes = {
        @Index(name = "idx_kyc_customer", columnList = "customer_id,documentType")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class KYCDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(length = 32)
    private String documentType; // PAN, AADHAAR, PASSPORT

    @Column(length = 64)
    private String documentNumberMasked;

    @Column(length = 32)
    private String status; // PENDING, APPROVED, REJECTED

    private String issuedBy;

    private LocalDate expiryDate;

    private String fileRef; // external file key

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}


