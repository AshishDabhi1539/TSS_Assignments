package com.tss.banking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.OtpVerificationToken;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.OtpType;

@Repository
public interface OtpVerificationTokenRepository extends JpaRepository<OtpVerificationToken, Long> {
    
    Optional<OtpVerificationToken> findByUserAndOtpTypeAndUsedFalseAndExpiredFalse(User user, OtpType otpType);
    
    List<OtpVerificationToken> findByUserAndOtpType(User user, OtpType otpType);
    
    @Query("SELECT t FROM OtpVerificationToken t WHERE t.user = :user AND t.otpType = :otpType AND t.used = false AND t.expired = false ORDER BY t.createdAt DESC")
    Optional<OtpVerificationToken> findLatestActiveTokenByUserAndType(@Param("user") User user, @Param("otpType") OtpType otpType);
    
    @Modifying
    @Query("UPDATE OtpVerificationToken t SET t.expired = true WHERE t.user = :user AND t.otpType = :otpType AND t.used = false AND t.expired = false")
    void expireActiveTokensByUserAndType(@Param("user") User user, @Param("otpType") OtpType otpType);
    
    @Modifying
    @Query("DELETE FROM OtpVerificationToken t WHERE t.expiresAt < :cutoffTime")
    void deleteExpiredTokens(@Param("cutoffTime") LocalDateTime cutoffTime);
    
    @Query("SELECT COUNT(t) FROM OtpVerificationToken t WHERE t.user = :user AND t.otpType = :otpType AND t.createdAt > :since")
    long countTokensCreatedSince(@Param("user") User user, @Param("otpType") OtpType otpType, @Param("since") LocalDateTime since);
}
