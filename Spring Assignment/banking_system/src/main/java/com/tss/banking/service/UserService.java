package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.AdminCreationRequestDto;
import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto dto);
    UserResponseDto createAdmin(AdminCreationRequestDto dto);
    UserResponseDto getUserById(Long id);
    UserResponseDto approveUser(Long id);
    UserResponseDto rejectUser(Long id);
    List<UserResponseDto> getPendingUsers();
    UserResponseDto getCurrentUserProfile(String email);
}