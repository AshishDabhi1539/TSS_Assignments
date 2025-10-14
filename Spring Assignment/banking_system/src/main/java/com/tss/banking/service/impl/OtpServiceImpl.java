package com.tss.banking.service.impl;

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
    }
}
