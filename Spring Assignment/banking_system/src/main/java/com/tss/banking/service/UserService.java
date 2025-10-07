package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.AdminCreationRequestDto;
import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto dto);
    boolean verifyEmailOtp(String email, String otp);
    void resendEmailVerificationOtp(String email);
    boolean resetPasswordWithOtp(String email, String otp, String newPassword);
    void sendPasswordResetOtp(String email);
    UserResponseDto createAdmin(AdminCreationRequestDto dto);
    UserResponseDto getUserById(Long id);
    UserResponseDto approveUser(Long id, String adminEmail);
    UserResponseDto rejectUser(Long id);
    List<UserResponseDto> getPendingUsers();
    List<UserResponseDto> getEmailVerifiedUsers();
    UserResponseDto getCurrentUserProfile(String email);
}