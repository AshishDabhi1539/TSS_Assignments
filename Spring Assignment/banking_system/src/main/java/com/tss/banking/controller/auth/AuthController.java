package com.tss.banking.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.request.LoginRequestDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.dto.response.LoginResponseDto;
import com.tss.banking.entity.User;
<<<<<<< HEAD
=======
import com.tss.banking.entity.enums.UserStatus;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.security.JwtUtil;
import com.tss.banking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
<<<<<<< HEAD
=======
import com.tss.banking.dto.request.OtpVerificationRequestDto;
import com.tss.banking.dto.request.PasswordResetRequestDto;
import com.tss.banking.dto.request.EmailRequestDto;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and Registration APIs")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with email and password")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto dto) {
        // Force CUSTOMER role for public registration
        dto.setRole("CUSTOMER");
        return ResponseEntity.ok(userService.registerUser(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user (Customer/Admin/SuperAdmin) and return JWT token")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
<<<<<<< HEAD
=======
            // First check if user exists and account status
            User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BankApiException("Invalid email or password"));

            // Check account status - only VERIFIED users can login
            if (user.getStatus() == UserStatus.PENDING) {
                throw new BankApiException("Please verify your email address before logging in");
            }
            if (user.getStatus() == UserStatus.EMAIL_VERIFIED) {
                throw new BankApiException("Your account is pending admin approval");
            }
            if (user.getStatus() == UserStatus.INACTIVE) {
                throw new BankApiException("Your account has been deactivated. Please contact support");
            }

>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
<<<<<<< HEAD
            User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BankApiException("User not found"));

=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
            String token = jwtUtil.generateToken(userDetails.getUsername(), user.getRole().name());

            return ResponseEntity.ok(new LoginResponseDto(token, user.getEmail(), 
                user.getRole().name(), user.getId()));

        } catch (Exception e) {
            throw new BankApiException("Invalid email or password");
        }
    }
<<<<<<< HEAD
=======

    @PostMapping("/verify-otp")
    @Operation(summary = "Verify email OTP", description = "Verify the OTP sent to user's email for registration")
    public ResponseEntity<String> verifyEmailOtp(@Valid @RequestBody OtpVerificationRequestDto request) {
        
        boolean isValid = userService.verifyEmailOtp(request.getEmail(), request.getOtp());
        
        if (isValid) {
            return ResponseEntity.ok("Email verified successfully. Your account is now pending admin approval.");
        } else {
            throw new BankApiException("Invalid or expired OTP");
        }
    }

    @PostMapping("/resend-otp")
    @Operation(summary = "Resend email verification OTP", description = "Resend OTP for email verification")
    public ResponseEntity<String> resendEmailVerificationOtp(@Valid @RequestBody EmailRequestDto request) {
        
        userService.resendEmailVerificationOtp(request.getEmail());
        return ResponseEntity.ok("OTP has been resent to your email address");
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Send password reset OTP", description = "Send OTP to user's email for password reset")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody EmailRequestDto request) {
        
        userService.sendPasswordResetOtp(request.getEmail());
        return ResponseEntity.ok("Password reset OTP has been sent to your email address");
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset password with OTP", description = "Reset password using OTP verification")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetRequestDto request) {
        
        boolean isValid = userService.resetPasswordWithOtp(request.getEmail(), request.getOtp(), request.getNewPassword());
        
        if (isValid) {
            return ResponseEntity.ok("Password has been reset successfully");
        } else {
            throw new BankApiException("Invalid or expired OTP");
        }
    }
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}