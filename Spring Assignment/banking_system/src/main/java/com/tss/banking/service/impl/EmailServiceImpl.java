package com.tss.banking.service.impl;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Commented out direct Spring Mail types to allow compilation if mail deps are absent
// intentionally disabled direct mail imports
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import org.springframework.stereotype.Service;

import com.tss.banking.entity.User;
import com.tss.banking.service.EmailService;

<<<<<<< HEAD
// intentionally disabled jakarta mail import

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    // @Autowired
    // private JavaMailSender mailSender;

    // keeping fallback logger-only mode; FROM not used
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public void sendAccountCreationEmail(User user, String accountNumber, String tempPassword) {
        String subject = "Welcome to TSS Bank - Account Created Successfully";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Welcome to TSS Bank! Your account has been created successfully.\n\n" +
            "Account Details:\n" +
            "Account Number: %s\n" +
            "Account Holder: %s %s\n" +
            "Temporary Password: %s\n\n" +
            "Please login and change your password immediately for security.\n\n" +
            "Thank you for choosing TSS Bank!\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            accountNumber,
            user.getFirstName(), user.getLastName(),
            tempPassword
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Account creation email sent to: {}", user.getEmail());
    }

    @Override
    public void sendAccountActivationEmail(User user, String accountNumber) {
        String subject = "TSS Bank - Account Activated";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your account has been activated successfully.\n\n" +
            "Account Number: %s\n" +
            "Status: ACTIVE\n\n" +
            "You can now access all banking services.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), accountNumber
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Account activation email sent to: {}", user.getEmail());
    }

    @Override
    public void sendAccountDeactivationEmail(User user, String accountNumber, String reason) {
        String subject = "TSS Bank - Account Deactivated";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your account has been deactivated.\n\n" +
            "Account Number: %s\n" +
            "Reason: %s\n\n" +
            "Please contact customer support for assistance.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), accountNumber, reason
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Account deactivation email sent to: {}", user.getEmail());
    }

    @Override
    public void sendPasswordResetEmail(User user, String resetToken) {
        String subject = "TSS Bank - Password Reset Request";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "You have requested a password reset for your TSS Bank account.\n\n" +
            "Reset Token: %s\n" +
            "This token is valid for 15 minutes.\n\n" +
            "If you did not request this, please ignore this email.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), resetToken
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Password reset email sent to: {}", user.getEmail());
    }

    @Override
    public void sendLoginOtpEmail(User user, String otp) {
        String subject = "TSS Bank - Login OTP";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your login OTP is: %s\n\n" +
            "This OTP is valid for 5 minutes.\n" +
            "Do not share this OTP with anyone.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), otp
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Login OTP email sent to: {}", user.getEmail());
    }

    @Override
    public void sendPasswordChangeConfirmationEmail(User user) {
        String subject = "TSS Bank - Password Changed Successfully";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your password has been changed successfully.\n\n" +
            "Time: %s\n\n" +
            "If you did not make this change, please contact us immediately.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            LocalDateTime.now().format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Password change confirmation email sent to: {}", user.getEmail());
    }

    @Override
    public void sendTransactionNotificationEmail(User user, String transactionType, BigDecimal amount, String accountNumber, String transactionId) {
        String subject = "TSS Bank - Transaction Notification";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "A transaction has been processed on your account.\n\n" +
            "Transaction Details:\n" +
            "Type: %s\n" +
            "Amount: ₹%s\n" +
            "Account: %s\n" +
            "Transaction ID: %s\n" +
            "Time: %s\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            transactionType, amount, accountNumber, transactionId,
            LocalDateTime.now().format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Transaction notification email sent to: {}", user.getEmail());
    }

    @Override
    public void sendLowBalanceAlertEmail(User user, BigDecimal currentBalance, String accountNumber) {
        String subject = "TSS Bank - Low Balance Alert";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your account balance is running low.\n\n" +
            "Account Number: %s\n" +
            "Current Balance: ₹%s\n\n" +
            "Please add funds to avoid service interruption.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), accountNumber, currentBalance
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Low balance alert email sent to: {}", user.getEmail());
    }

    @Override
    public void sendDailyTransactionSummaryEmail(User user, String accountNumber) {
        String subject = "TSS Bank - Daily Transaction Summary";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Here is your daily transaction summary for account %s.\n\n" +
            "Date: %s\n\n" +
            "Please login to your account for detailed information.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), accountNumber,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Daily transaction summary email sent to: {}", user.getEmail());
    }

    @Override
    public void sendMonthlyStatementEmail(User user, String accountNumber, byte[] statementPdf) {
        String subject = "TSS Bank - Monthly Statement";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Please find attached your monthly statement for account %s.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), accountNumber
        );
        
        // For now, send without attachment (PDF functionality can be added later)
        sendEmail(user.getEmail(), subject, body);
        log.info("Monthly statement email sent to: {}", user.getEmail());
    }

    @Override
    public void sendBeneficiaryCreationEmail(User user, String beneficiaryAlias, String accountNumber, LocalDateTime coolingPeriodEnds, String activationOtp) {
        String subject = "TSS Bank - Beneficiary Added Successfully";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "A new beneficiary has been added to your account.\n\n" +
            "Beneficiary Details:\n" +
            "Alias: %s\n" +
            "Account Number: %s\n" +
            "Status: COOLING_PERIOD\n\n" +
            "Cooling Period Ends: %s\n" +
            "Activation OTP: %s\n\n" +
            "You can activate this beneficiary after the cooling period using the OTP above.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            beneficiaryAlias, accountNumber,
            coolingPeriodEnds.format(DATE_FORMATTER), activationOtp
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Beneficiary creation email sent to: {}", user.getEmail());
    }

    @Override
    public void sendBeneficiaryActivationSuccessEmail(User user, String beneficiaryAlias, String accountNumber) {
        String subject = "TSS Bank - Beneficiary Activated Successfully";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your beneficiary has been activated successfully.\n\n" +
            "Beneficiary Details:\n" +
            "Alias: %s\n" +
            "Account Number: %s\n" +
            "Status: ACTIVE\n\n" +
            "You can now transfer funds to this beneficiary.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), beneficiaryAlias, accountNumber
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Beneficiary activation success email sent to: {}", user.getEmail());
    }

    @Override
    public void sendBeneficiaryApprovalEmail(User user, String beneficiaryAlias, String status, String remarks) {
        String subject = "TSS Bank - Beneficiary " + status.toUpperCase();
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your beneficiary request has been %s.\n\n" +
            "Beneficiary Alias: %s\n" +
            "Status: %s\n" +
            "Remarks: %s\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            status.toLowerCase(), beneficiaryAlias, status.toUpperCase(), remarks
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Beneficiary approval email sent to: {}", user.getEmail());
    }

    @Override
    public void sendTransferOtpEmail(User user, String beneficiaryAlias, BigDecimal amount, String otp, int validityMinutes) {
        String subject = "TSS Bank - Transfer OTP";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your transfer OTP is: %s\n\n" +
            "Transfer Details:\n" +
            "To: %s\n" +
            "Amount: ₹%s\n" +
            "Valid for: %d minutes\n\n" +
            "Do not share this OTP with anyone.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(), otp,
            beneficiaryAlias, amount, validityMinutes
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Transfer OTP email sent to: {}", user.getEmail());
    }

    @Override
    public void sendTransferSuccessEmail(User user, String beneficiaryAlias, String accountNumber, BigDecimal amount, String transactionId) {
        String subject = "TSS Bank - Transfer Successful";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your transfer has been completed successfully.\n\n" +
            "Transfer Details:\n" +
            "To: %s (%s)\n" +
            "Amount: ₹%s\n" +
            "Transaction ID: %s\n" +
            "Time: %s\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            beneficiaryAlias, accountNumber, amount, transactionId,
            LocalDateTime.now().format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Transfer success email sent to: {}", user.getEmail());
    }

    @Override
    public void sendTransferFailureEmail(User user, String beneficiaryAlias, BigDecimal amount, String reason) {
        String subject = "TSS Bank - Transfer Failed";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your transfer has failed.\n\n" +
            "Transfer Details:\n" +
            "To: %s\n" +
            "Amount: ₹%s\n" +
            "Reason: %s\n" +
            "Time: %s\n\n" +
            "Please try again or contact customer support.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            beneficiaryAlias, amount, reason,
            LocalDateTime.now().format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Transfer failure email sent to: {}", user.getEmail());
    }

    @Override
    public void sendFundsReceivedEmail(User user, String senderName, BigDecimal amount, String transactionId) {
        String subject = "TSS Bank - Funds Received";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "You have received funds in your account.\n\n" +
            "Transfer Details:\n" +
            "From: %s\n" +
            "Amount: ₹%s\n" +
            "Transaction ID: %s\n" +
            "Time: %s\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            senderName, amount, transactionId,
            LocalDateTime.now().format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Funds received email sent to: {}", user.getEmail());
    }

    @Override
    public void sendWelcomeEmail(User user, String tempPassword) {
        String subject = "Welcome to TSS Bank";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Welcome to TSS Bank!\n\n" +
            "Your account has been created with the following credentials:\n" +
            "Email: %s\n" +
            "Temporary Password: %s\n\n" +
            "Please login and change your password immediately.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            user.getEmail(), tempPassword
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Welcome email sent to: {}", user.getEmail());
    }

    @Override
    public void sendAccountStatusChangeEmail(User user, String newStatus, String reason) {
        String subject = "TSS Bank - Account Status Changed";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "Your account status has been changed.\n\n" +
            "New Status: %s\n" +
            "Reason: %s\n" +
            "Time: %s\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            newStatus, reason,
            LocalDateTime.now().format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("Account status change email sent to: {}", user.getEmail());
    }

    @Override
    public void sendSystemMaintenanceEmail(User user, LocalDateTime maintenanceStart, LocalDateTime maintenanceEnd) {
        String subject = "TSS Bank - Scheduled Maintenance";
        String body = String.format(
            "Dear %s %s,\n\n" +
            "We will be performing scheduled maintenance on our systems.\n\n" +
            "Maintenance Window:\n" +
            "Start: %s\n" +
            "End: %s\n\n" +
            "Banking services may be temporarily unavailable during this time.\n\n" +
            "Best regards,\n" +
            "TSS Bank Team",
            user.getFirstName(), user.getLastName(),
            maintenanceStart.format(DATE_FORMATTER),
            maintenanceEnd.format(DATE_FORMATTER)
        );
        
        sendEmail(user.getEmail(), subject, body);
        log.info("System maintenance email sent to: {}", user.getEmail());
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        // Fallback: Log email content instead of sending (to compile without mail dependency)
        log.info("[Email Fallback] To: {} | Subject: {}\n{}", to, subject, body);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        log.info("[Email Fallback HTML] To: {} | Subject: {}\n{}", to, subject, htmlBody);
=======
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:noreply@bankingsystem.com}")
    private String fromEmail;

    @Override
    public void sendOtpEmail(User user, String otp, String otpType) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(user.getEmail());
            
            if ("EMAIL_VERIFICATION".equals(otpType)) {
                message.setSubject("Email Verification - Banking System");
                message.setText(buildEmailVerificationOtpTemplate(user.getFirstName(), otp));
            } else if ("PASSWORD_RESET".equals(otpType)) {
                message.setSubject("Password Reset - Banking System");
                message.setText(buildPasswordResetOtpTemplate(user.getFirstName(), otp));
            }
            
            mailSender.send(message);
            log.info("OTP email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send OTP email to: {}", user.getEmail(), e);
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    @Override
    public void sendWelcomeEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(user.getEmail());
            message.setSubject("Welcome to Banking System - Email Verified");
            message.setText(buildWelcomeTemplate(user.getFirstName()));
            
            mailSender.send(message);
            log.info("Welcome email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send welcome email to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendAccountApprovedEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(user.getEmail());
            message.setSubject("Account Approved - Banking System");
            message.setText(buildAccountApprovedTemplate(user.getFirstName()));
            
            mailSender.send(message);
            log.info("Account approved email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send account approved email to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendAccountRejectedEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(user.getEmail());
            message.setSubject("Account Application Status - Banking System");
            message.setText(buildAccountRejectedTemplate(user.getFirstName()));
            
            mailSender.send(message);
            log.info("Account rejected email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send account rejected email to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendPasswordChangeSuccessEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(user.getEmail());
            message.setSubject("Password Changed Successfully - Banking System");
            message.setText(buildPasswordChangeSuccessTemplate(user.getFirstName()));
            
            mailSender.send(message);
            log.info("Password change success email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send password change success email to: {}", user.getEmail(), e);
        }
    }

    private String buildEmailVerificationOtpTemplate(String firstName, String otp) {
        return String.format("""
            Dear %s,
            
            Thank you for registering with Banking System!
            
            Your email verification code is: %s
            
            This code will expire in 10 minutes and can be used up to 5 times.
            
            Please enter this code to verify your email address and complete your registration.
            
            After email verification, your account will be pending admin approval before you can start using our services.
            
            If you didn't request this verification, please ignore this email.
            
            Best regards,
            Banking System Team
            
            Security Tips:
            - Never share your OTP with anyone
            - Our team will never ask for your OTP over phone or email
            - If you suspect any suspicious activity, contact our support immediately
            """, firstName, otp);
    }

    private String buildPasswordResetOtpTemplate(String firstName, String otp) {
        return String.format("""
            Dear %s,
            
            You have requested to reset your password for your Banking System account.
            
            Your password reset code is: %s
            
            This code will expire in 10 minutes and can be used up to 5 times.
            
            Please enter this code along with your new password to complete the password reset process.
            
            If you didn't request this password reset, please ignore this email and your password will remain unchanged.
            
            Best regards,
            Banking System Team
            
            Security Tips:
            - Never share your OTP with anyone
            - Use a strong password with a mix of letters, numbers, and special characters
            - Avoid using the same password for multiple accounts
            """, firstName, otp);
    }

    private String buildWelcomeTemplate(String firstName) {
        return String.format("""
            Dear %s,
            
            Congratulations! Your email has been successfully verified.
            
            Your account is now pending admin approval. You will receive another email once your account has been reviewed and approved by our team.
            
            This process typically takes 1-2 business days.
            
            What happens next:
            1. Our admin team will review your account details
            2. You'll receive an email notification once approved
            3. You can then log in and start using our banking services
            
            Thank you for choosing Banking System!
            
            Best regards,
            Banking System Team
            """, firstName);
    }

    private String buildAccountApprovedTemplate(String firstName) {
        return String.format("""
            Dear %s,
            
            Great news! Your Banking System account has been approved and is now active.
            
            You can now log in to your account and access all our banking services including:
            
            ✓ Account Management
            ✓ Fund Transfers
            ✓ Transaction History
            ✓ Beneficiary Management
            ✓ Account Statements
            ✓ Profile Management
            
            Login to get started: [Your Banking System Login URL]
            
            Welcome to Banking System! We're excited to serve you.
            
            Best regards,
            Banking System Team
            
            Need Help?
            Contact our customer support for any assistance.
            """, firstName);
    }

    private String buildAccountRejectedTemplate(String firstName) {
        return String.format("""
            Dear %s,
            
            Thank you for your interest in Banking System.
            
            After careful review, we regret to inform you that your account application has not been approved at this time.
            
            This decision may be due to various factors including incomplete information or verification requirements.
            
            If you believe this is an error or would like to reapply, please contact our customer support team who will be happy to assist you.
            
            Thank you for considering Banking System.
            
            Best regards,
            Banking System Team
            """, firstName);
    }

    private String buildPasswordChangeSuccessTemplate(String firstName) {
        return String.format("""
            Dear %s,
            
            Your password has been successfully changed for your Banking System account.
            
            If you made this change, no further action is required.
            
            If you did not make this change, please contact our support team immediately and consider:
            - Changing your password again
            - Reviewing your account for any unauthorized activity
            - Enabling additional security measures
            
            Account Security Tips:
            - Use a unique, strong password
            - Enable two-factor authentication if available
            - Never share your login credentials
            - Log out completely when using shared computers
            
            Best regards,
            Banking System Team
            """, firstName);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }
}
