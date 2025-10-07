package com.tss.banking.service;

import com.tss.banking.entity.User;

public interface EmailService {
    void sendOtpEmail(User user, String otp, String otpType);
    void sendWelcomeEmail(User user);
    void sendAccountApprovedEmail(User user);
    void sendAccountRejectedEmail(User user);
    void sendPasswordChangeSuccessEmail(User user);
}
