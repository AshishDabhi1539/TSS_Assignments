package com.tss.banking.service;

import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.OtpType;

public interface OtpService {
    void generateAndSendOtp(User user, OtpType otpType);
    boolean verifyOtp(User user, String otp, OtpType otpType);
    void resendOtp(User user, OtpType otpType);
    void expireActiveOtps(User user, OtpType otpType);
    void cleanupExpiredTokens();
}
