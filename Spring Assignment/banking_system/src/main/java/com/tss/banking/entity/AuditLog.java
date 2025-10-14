package com.tss.banking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "audit_logs", indexes = {
        @Index(name = "idx_audit_created_at", columnList = "timestamp")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action; // e.g., CREATE_ACCOUNT, APPROVE_CUSTOMER
    private Long entityId; // Reference to the entity affected
    private LocalDateTime timestamp;
    private String performedBy; // e.g., customerId, adminId
    private String entityType;
    @Column(length = 64)
    private String requestId;
    private String actorRole;
    @Column(length = 512)
    private String payload;

    @PrePersist
    void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }
}