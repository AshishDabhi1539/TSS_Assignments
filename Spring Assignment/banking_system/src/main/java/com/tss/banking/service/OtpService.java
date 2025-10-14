package com.tss.banking.service;

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
}
