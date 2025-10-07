package com.tss.banking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.request.AdminCreationRequestDto;
import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.OtpType;
import com.tss.banking.entity.enums.UserStatus;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.AddressService;
import com.tss.banking.service.EmailService;
import com.tss.banking.service.OtpService;
import com.tss.banking.service.UserService;
import com.tss.banking.entity.enums.AddressType;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AddressService addressService;

    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {
        if ("SUPERADMIN".equalsIgnoreCase(dto.getRole())) {
            throw new BankApiException("SuperAdmin registration is not allowed via this process.");
        }
        if (!("CUSTOMER".equalsIgnoreCase(dto.getRole()) || "ADMIN".equalsIgnoreCase(dto.getRole()))) {
            throw new BankApiException("Invalid role for registration: " + dto.getRole());
        }

        // Check if email or phone already exists
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new BankApiException("Email already exists: " + dto.getEmail());
        }
        if (userRepo.existsByPhone(dto.getPhone())) {
            throw new BankApiException("Phone number already exists: " + dto.getPhone());
        }

        User user = mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(UserStatus.PENDING);
        try {
            user.setRole(RoleType.valueOf(dto.getRole().toUpperCase()));
        } catch (Exception ex) {
            throw new BankApiException("Invalid role: " + dto.getRole());
        }
        User savedUser = userRepo.save(user);
        
        // Create address for the user using the unified address system
        try {
            addressService.createAddressForEntity(
                "USER", 
                savedUser.getId(), 
                AddressType.PERMANENT,
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getCity(),
                dto.getState(),
                dto.getPostalCode(),
                "India", // Auto-assign India
                null // Landmark null
            );
        } catch (Exception e) {
            // If address creation fails, rollback user creation
            userRepo.delete(savedUser);
            throw new BankApiException("Failed to create user address: " + e.getMessage());
        }
        
        // Send OTP for email verification
        otpService.generateAndSendOtp(savedUser, OtpType.EMAIL_VERIFICATION);
        
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + id));
        return mapper.map(user, UserResponseDto.class);
    }


    @Override
    public List<UserResponseDto> getPendingUsers() {
        List<User> pendingUsers = userRepo.findByStatus(UserStatus.PENDING);
        return pendingUsers.stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .toList();
    }

    @Override
    public UserResponseDto getCurrentUserProfile(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new BankApiException("User not found with email: " + email));
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    @Transactional
    public boolean verifyEmailOtp(String email, String otp) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new BankApiException("User not found with email: " + email));
        
        if (user.getStatus() != UserStatus.PENDING) {
            throw new BankApiException("User email is already verified or account is inactive");
        }
        
        boolean isValid = otpService.verifyOtp(user, otp, OtpType.EMAIL_VERIFICATION);
        
        if (isValid) {
            user.setStatus(UserStatus.EMAIL_VERIFIED);
            user.setEmailVerified(true);
            userRepo.save(user);
            emailService.sendWelcomeEmail(user);
        }
        
        return isValid;
    }

    @Override
    public void resendEmailVerificationOtp(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new BankApiException("User not found with email: " + email));
        
        if (user.getStatus() != UserStatus.PENDING) {
            throw new BankApiException("User email is already verified or account is inactive");
        }
        
        otpService.resendOtp(user, OtpType.EMAIL_VERIFICATION);
    }

    @Override
    public void sendPasswordResetOtp(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new BankApiException("User not found with email: " + email));
        
        if (user.getStatus() == UserStatus.INACTIVE) {
            throw new BankApiException("Account is inactive. Please contact support.");
        }
        
        otpService.generateAndSendOtp(user, OtpType.PASSWORD_RESET);
    }

    @Override
    @Transactional
    public boolean resetPasswordWithOtp(String email, String otp, String newPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new BankApiException("User not found with email: " + email));
        
        if (user.getStatus() == UserStatus.INACTIVE) {
            throw new BankApiException("Account is inactive. Please contact support.");
        }
        
        boolean isValid = otpService.verifyOtp(user, otp, OtpType.PASSWORD_RESET);
        
        if (isValid) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
            emailService.sendPasswordChangeSuccessEmail(user);
        }
        
        return isValid;
    }

    @Override
    public List<UserResponseDto> getEmailVerifiedUsers() {
        List<User> emailVerifiedUsers = userRepo.findByStatus(UserStatus.EMAIL_VERIFIED);
        return emailVerifiedUsers.stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDto approveUser(Long id, String adminEmail) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + id));
        
        if (user.getStatus() != UserStatus.EMAIL_VERIFIED) {
            throw new BankApiException("User must verify email before admin approval");
        }
        
        // Get admin user who is approving
        User adminUser = userRepo.findByEmail(adminEmail)
                .orElseThrow(() -> new BankApiException("Admin user not found"));
        
        user.setStatus(UserStatus.VERIFIED);
        user.setEnabled(true);
        user.setApprovedBy(adminUser);
        user.setApprovedAt(java.time.LocalDateTime.now());
        
        User savedUser = userRepo.save(user);
        emailService.sendAccountApprovedEmail(savedUser);
        
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    @Transactional
    public UserResponseDto rejectUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + id));
        
        user.setStatus(UserStatus.INACTIVE);
        User savedUser = userRepo.save(user);
        emailService.sendAccountRejectedEmail(savedUser);
        
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto createAdmin(AdminCreationRequestDto dto) {
        // Check if email or phone already exists
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new BankApiException("Email already exists: " + dto.getEmail());
        }
        if (userRepo.existsByPhone(dto.getPhone())) {
            throw new BankApiException("Phone number already exists: " + dto.getPhone());
        }

        User user = mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(UserStatus.VERIFIED); // Admin is auto-verified
        user.setRole(RoleType.ADMIN);
        user.setSoftDeleted(false);
        user.setEmailVerified(true);
        user.setEnabled(true);
        
        // Auto-approve admin users - set as self-approved
        user.setApprovedBy(user); // Self-approved for admin creation
        user.setApprovedAt(java.time.LocalDateTime.now());
        
        User savedUser = userRepo.save(user);
        
        // Create address for the admin using the unified address system
        try {
            addressService.createAddressForEntity(
                "USER", 
                savedUser.getId(), 
                AddressType.PERMANENT,
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getCity(),
                dto.getState(),
                dto.getPostalCode(),
                "India", // Auto-assign India
                null // Landmark null
            );
        } catch (Exception e) {
            // If address creation fails, rollback user creation
            userRepo.delete(savedUser);
            throw new BankApiException("Failed to create admin address: " + e.getMessage());
        }
        
        return mapper.map(savedUser, UserResponseDto.class);
    }
}