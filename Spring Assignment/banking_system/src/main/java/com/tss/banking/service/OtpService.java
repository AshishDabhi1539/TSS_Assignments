package com.tss.banking.service;

<<<<<<< HEAD
public interface OtpService {
    
    /**
     * Generate OTP for a user with default length (6 digits) and validity (5 minutes)
     */
    String generateOtp(String userEmail, String purpose);
    
    /**
     * Generate OTP with custom length and validity
     */
    String generateOtp(String userEmail, String purpose, int length, int validityMinutes);
    
    /**
     * Verify OTP for a user and purpose
     */
    boolean verifyOtp(String userEmail, String otp, String purpose);
    
    /**
     * Check if OTP exists and is valid (without consuming it)
     */
    boolean isOtpValid(String userEmail, String purpose);
    
    /**
     * Invalidate/delete OTP for a user and purpose
     */
    void invalidateOtp(String userEmail, String purpose);
    
    /**
     * Get remaining validity time in minutes
     */
    long getRemainingValidityMinutes(String userEmail, String purpose);
    
    /**
     * Clean up expired OTPs (scheduled task)
     */
    void cleanupExpiredOtps();
=======
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.OtpType;

public interface OtpService {
    void generateAndSendOtp(User user, OtpType otpType);
    boolean verifyOtp(User user, String otp, OtpType otpType);
    void resendOtp(User user, OtpType otpType);
    void expireActiveOtps(User user, OtpType otpType);
    void cleanupExpiredTokens();
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}
