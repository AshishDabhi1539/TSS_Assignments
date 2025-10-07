package com.tss.banking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.tss.banking.entity.enums.OtpType;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "otp_verification_tokens", indexes = {
        @Index(name = "idx_otp_user_type", columnList = "user_id,otpType"),
        @Index(name = "idx_otp_expires_at", columnList = "expiresAt"),
        @Index(name = "idx_otp_created_at", columnList = "createdAt"),
        @Index(name = "idx_otp_used_expired", columnList = "used,expired")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"hashedOtp", "user"})
public class OtpVerificationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false, length = 255)
    private String hashedOtp; // Store BCrypt hashed OTP for security
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private OtpType otpType;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    
    @Column(nullable = false)
    private Integer attemptCount = 0;
    
    @Column(nullable = false)
    private Integer maxAttempts = 5;
    
    @Column(nullable = false)
    private Boolean used = false;
    
    @Column(nullable = false)
    private Boolean expired = false;
    
    @Column
    private LocalDateTime lastAttemptAt;
    
    @Column
    private LocalDateTime lastResendAt;
    
    @PrePersist
    void onCreate() {
        if (expiresAt == null) {
            expiresAt = LocalDateTime.now().plusMinutes(10); // 10 minutes expiry
        }
        if (attemptCount == null) {
            attemptCount = 0;
        }
        if (maxAttempts == null) {
            maxAttempts = 5;
        }
        if (used == null) {
            used = false;
        }
        if (expired == null) {
            expired = false;
        }
    }
    
    public boolean isExpired() {
        return expired || LocalDateTime.now().isAfter(expiresAt);
    }
    
    public boolean isMaxAttemptsReached() {
        return attemptCount >= maxAttempts;
    }
    
    public boolean canResend() {
        if (lastResendAt == null) {
            return true;
        }
        return LocalDateTime.now().isAfter(lastResendAt.plusSeconds(60)); // 60 seconds rate limit
    }
    
    public void incrementAttemptCount() {
        this.attemptCount++;
        this.lastAttemptAt = LocalDateTime.now();
    }
    
    public void markAsUsed() {
        this.used = true;
    }
    
    public void markAsExpired() {
        this.expired = true;
    }
    
    public void updateResendTime() {
        this.lastResendAt = LocalDateTime.now();
    }
}
