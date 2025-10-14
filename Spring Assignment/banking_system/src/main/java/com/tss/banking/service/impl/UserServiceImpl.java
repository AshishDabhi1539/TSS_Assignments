package com.tss.banking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.UserRequestDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.UserStatus;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + id));
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto approveUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + id));
        user.setStatus(UserStatus.VERIFIED);
        User savedUser = userRepo.save(user);
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto rejectUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new BankApiException("User not found with ID: " + id));
        user.setStatus(UserStatus.INACTIVE);
        User savedUser = userRepo.save(user);
        return mapper.map(savedUser, UserResponseDto.class);
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
}