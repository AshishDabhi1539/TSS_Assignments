package com.tss.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tss.banking.entity.User;
import com.tss.banking.service.EmailService;

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
    }
}
