package com.tss.banking.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.entity.OtpVerificationToken;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.OtpType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.OtpVerificationTokenRepository;
import com.tss.banking.service.EmailService;
import com.tss.banking.service.OtpService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpVerificationTokenRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public void generateAndSendOtp(User user, OtpType otpType) {
        // Check rate limiting - max 3 OTPs per hour
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        long recentOtpCount = otpRepository.countTokensCreatedSince(user, otpType, oneHourAgo);
        
        if (recentOtpCount >= 3) {
            throw new BankApiException("Too many OTP requests. Please try again after an hour.");
        }

        // Expire any existing active tokens for this user and type
        expireActiveOtps(user, otpType);

        // Generate 6-digit OTP
        String otp = generateSixDigitOtp();
        String hashedOtp = passwordEncoder.encode(otp);

        // Create new OTP token
        OtpVerificationToken token = new OtpVerificationToken();
        token.setUser(user);
        token.setHashedOtp(hashedOtp);
        token.setOtpType(otpType);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10)); // 10 minutes expiry
        token.setMaxAttempts(5); // 5 attempts max

        otpRepository.save(token);

        // Send OTP via email
        emailService.sendOtpEmail(user, otp, otpType.name());
        
        log.info("OTP generated and sent for user: {} with type: {}", user.getEmail(), otpType);
    }

    @Override
    public boolean verifyOtp(User user, String otp, OtpType otpType) {
        Optional<OtpVerificationToken> tokenOpt = otpRepository
                .findLatestActiveTokenByUserAndType(user, otpType);

        if (tokenOpt.isEmpty()) {
            throw new BankApiException("No valid OTP found. Please request a new OTP.");
        }

        OtpVerificationToken token = tokenOpt.get();

        // Check if token is expired
        if (token.isExpired()) {
            token.markAsExpired();
            otpRepository.save(token);
            throw new BankApiException("OTP has expired. Please request a new OTP.");
        }

        // Check if max attempts reached
        if (token.isMaxAttemptsReached()) {
            token.markAsExpired();
            otpRepository.save(token);
            throw new BankApiException("Maximum OTP attempts exceeded. Please request a new OTP.");
        }

        // Increment attempt count
        token.incrementAttemptCount();

        // Verify OTP
        boolean isValid = passwordEncoder.matches(otp, token.getHashedOtp());

        if (isValid) {
            token.markAsUsed();
            log.info("OTP verified successfully for user: {} with type: {}", user.getEmail(), otpType);
        } else {
            log.warn("Invalid OTP attempt for user: {} with type: {}, attempts: {}", 
                    user.getEmail(), otpType, token.getAttemptCount());
        }

        otpRepository.save(token);
        return isValid;
    }

    @Override
    public void resendOtp(User user, OtpType otpType) {
        Optional<OtpVerificationToken> tokenOpt = otpRepository
                .findLatestActiveTokenByUserAndType(user, otpType);

        if (tokenOpt.isPresent()) {
            OtpVerificationToken token = tokenOpt.get();
            
            // Check rate limiting - 60 seconds between resends
            if (!token.canResend()) {
                throw new BankApiException("Please wait 60 seconds before requesting another OTP.");
            }
        }

        // Generate and send new OTP
        generateAndSendOtp(user, otpType);
    }

    @Override
    public void expireActiveOtps(User user, OtpType otpType) {
        otpRepository.expireActiveTokensByUserAndType(user, otpType);
        log.debug("Expired active OTPs for user: {} with type: {}", user.getEmail(), otpType);
    }

    @Override
    public void cleanupExpiredTokens() {
        LocalDateTime cutoffTime = LocalDateTime.now().minusDays(1); // Keep tokens for 1 day
        otpRepository.deleteExpiredTokens(cutoffTime);
        log.info("Cleaned up expired OTP tokens older than: {}", cutoffTime);
    }

    private String generateSixDigitOtp() {
        // Generate 6-digit OTP using SecureRandom
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }
}
