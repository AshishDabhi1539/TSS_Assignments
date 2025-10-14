package com.tss.banking.service;

import java.util.List;

<<<<<<< HEAD
=======
import com.tss.banking.dto.request.AdminCreationRequestDto;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto dto);
<<<<<<< HEAD
    UserResponseDto getUserById(Long id);
    UserResponseDto approveUser(Long id);
    UserResponseDto rejectUser(Long id);
    List<UserResponseDto> getPendingUsers();
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    UserResponseDto getCurrentUserProfile(String email);
}