package com.tss.banking.entity;

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
<<<<<<< HEAD
=======
import jakarta.persistence.PreUpdate;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "notifications", indexes = {
<<<<<<< HEAD
        @Index(name = "idx_notifications_customer", columnList = "customer_id,sentAt")
=======
        @Index(name = "idx_notifications_user", columnList = "user_id,createdAt")
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
<<<<<<< HEAD
    @JoinColumn(name = "customer_id")
    private User customer;
=======
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 32)
    private String notificationType; // ACCOUNT_UPDATE, TRANSACTION, KYC_STATUS, etc.

    @Column(length = 128)
    private String title;

    @Column(length = 1024)
    private String message;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

    @Column(length = 16)
    private String channel; // EMAIL, SMS, INAPP

<<<<<<< HEAD
    private String subject;

    @Column(length = 1024)
    private String body;

    @Column(length = 32)
    private String status; // PENDING, SENT, FAILED

    private LocalDateTime sentAt;
=======
    @Column(length = 32)
    private String status; // PENDING, SENT, FAILED

    @Column(length = 512)
    private String metadata; // JSON string for additional data

    private LocalDateTime sentAt;
    
    private LocalDateTime readAt;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

    @PrePersist
    void onCreate() {
        if (status == null) {
            status = "PENDING";
        }
<<<<<<< HEAD
=======
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }
}


