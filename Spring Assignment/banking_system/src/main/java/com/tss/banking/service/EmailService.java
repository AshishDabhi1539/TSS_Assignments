package com.tss.banking.service;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tss.banking.entity.User;

public interface EmailService {
    
    // Account related emails
    void sendAccountCreationEmail(User user, String accountNumber, String tempPassword);
    void sendAccountActivationEmail(User user, String accountNumber);
    void sendAccountDeactivationEmail(User user, String accountNumber, String reason);
    
    // Authentication emails
    void sendPasswordResetEmail(User user, String resetToken);
    void sendLoginOtpEmail(User user, String otp);
    void sendPasswordChangeConfirmationEmail(User user);
    
    // Transaction emails
    void sendTransactionNotificationEmail(User user, String transactionType, BigDecimal amount, String accountNumber, String transactionId);
    void sendLowBalanceAlertEmail(User user, BigDecimal currentBalance, String accountNumber);
    void sendDailyTransactionSummaryEmail(User user, String accountNumber);
    void sendMonthlyStatementEmail(User user, String accountNumber, byte[] statementPdf);
    
    // Beneficiary emails
    void sendBeneficiaryCreationEmail(User user, String beneficiaryAlias, String accountNumber, LocalDateTime coolingPeriodEnds, String activationOtp);
    void sendBeneficiaryActivationSuccessEmail(User user, String beneficiaryAlias, String accountNumber);
    void sendBeneficiaryApprovalEmail(User user, String beneficiaryAlias, String status, String remarks);
    
    // Transfer emails
    void sendTransferOtpEmail(User user, String beneficiaryAlias, BigDecimal amount, String otp, int validityMinutes);
    void sendTransferSuccessEmail(User user, String beneficiaryAlias, String accountNumber, BigDecimal amount, String transactionId);
    void sendTransferFailureEmail(User user, String beneficiaryAlias, BigDecimal amount, String reason);
    void sendFundsReceivedEmail(User user, String senderName, BigDecimal amount, String transactionId);
    
    // Admin emails
    void sendWelcomeEmail(User user, String tempPassword);
    void sendAccountStatusChangeEmail(User user, String newStatus, String reason);
    void sendSystemMaintenanceEmail(User user, LocalDateTime maintenanceStart, LocalDateTime maintenanceEnd);
    
    // Generic email
    void sendEmail(String to, String subject, String body);
    void sendHtmlEmail(String to, String subject, String htmlBody);
=======
import com.tss.banking.entity.User;

public interface EmailService {
    void sendOtpEmail(User user, String otp, String otpType);
    void sendWelcomeEmail(User user);
    void sendAccountApprovedEmail(User user);
    void sendAccountRejectedEmail(User user);
    void sendPasswordChangeSuccessEmail(User user);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}
