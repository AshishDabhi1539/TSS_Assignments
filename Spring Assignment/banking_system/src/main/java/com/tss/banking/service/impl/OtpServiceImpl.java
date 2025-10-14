package com.tss.banking.service.impl;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tss.banking.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

    private static final Logger log = LoggerFactory.getLogger(OtpServiceImpl.class);

    // In-memory storage for OTPs - In production, use Redis or database
    private final Map<String, OtpData> otpStorage = new ConcurrentHashMap<>();
    
    private static final int DEFAULT_OTP_LENGTH = 6;
    private static final int DEFAULT_VALIDITY_MINUTES = 5;
    private static final Random random = new Random();

    @Override
    public String generateOtp(String userEmail, String purpose) {
        return generateOtp(userEmail, purpose, DEFAULT_OTP_LENGTH, DEFAULT_VALIDITY_MINUTES);
    }

    @Override
    public String generateOtp(String userEmail, String purpose, int length, int validityMinutes) {
        // Generate random OTP
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        
        String otpValue = otp.toString();
        String key = generateKey(userEmail, purpose);
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(validityMinutes);
        
        // Store OTP
        otpStorage.put(key, new OtpData(otpValue, expiryTime));
        
        log.info("OTP generated for user: {} purpose: {} length: {} validity: {} minutes - OTP: {}", 
                userEmail, purpose, length, validityMinutes, otpValue);
        
        return otpValue;
    }

    @Override
    public boolean verifyOtp(String userEmail, String otp, String purpose) {
        String key = generateKey(userEmail, purpose);
        OtpData otpData = otpStorage.get(key);
        
        if (otpData == null) {
            log.warn("OTP verification failed - No OTP found for user: {} purpose: {}", userEmail, purpose);
            return false;
        }
        
        if (LocalDateTime.now().isAfter(otpData.getExpiryTime())) {
            log.warn("OTP verification failed - Expired OTP for user: {} purpose: {}", userEmail, purpose);
            otpStorage.remove(key); // Remove expired OTP
            return false;
        }
        
        if (!otpData.getOtp().equals(otp)) {
            log.warn("OTP verification failed - Invalid OTP for user: {} purpose: {}", userEmail, purpose);
            return false;
        }
        
        // OTP is valid - consume it (remove from storage)
        otpStorage.remove(key);
        log.info("OTP verified successfully for user: {} purpose: {}", userEmail, purpose);
        
        return true;
    }

    @Override
    public boolean isOtpValid(String userEmail, String purpose) {
        String key = generateKey(userEmail, purpose);
        OtpData otpData = otpStorage.get(key);
        
        if (otpData == null) {
            return false;
        }
        
        if (LocalDateTime.now().isAfter(otpData.getExpiryTime())) {
            otpStorage.remove(key); // Remove expired OTP
            return false;
        }
        
        return true;
    }

    @Override
    public void invalidateOtp(String userEmail, String purpose) {
        String key = generateKey(userEmail, purpose);
        otpStorage.remove(key);
        log.info("OTP invalidated for user: {} purpose: {}", userEmail, purpose);
    }

    @Override
    public long getRemainingValidityMinutes(String userEmail, String purpose) {
        String key = generateKey(userEmail, purpose);
        OtpData otpData = otpStorage.get(key);
        
        if (otpData == null) {
            return 0;
        }
        
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(otpData.getExpiryTime())) {
            otpStorage.remove(key);
            return 0;
        }
        
        return java.time.Duration.between(now, otpData.getExpiryTime()).toMinutes();
    }

    @Override
    @Scheduled(fixedRate = 300000) // Run every 5 minutes
    public void cleanupExpiredOtps() {
        LocalDateTime now = LocalDateTime.now();
        final int[] removedCount = {0};

        otpStorage.entrySet().removeIf(entry -> {
            if (now.isAfter(entry.getValue().getExpiryTime())) {
                removedCount[0]++;
                return true;
            }
            return false;
        });

        if (removedCount[0] > 0) {
            log.info("Cleaned up {} expired OTPs", removedCount[0]);
        }
    }

    private String generateKey(String userEmail, String purpose) {
        return userEmail + ":" + purpose;
    }

    // Inner class to store OTP data
    private static class OtpData {
        private final String otp;
        private final LocalDateTime expiryTime;

        public OtpData(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }
}
