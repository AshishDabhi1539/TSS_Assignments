package com.tss.banking.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String status;
    private String role;
    private boolean emailVerified;
    private boolean enabled;
    private LocalDateTime createdAt;
    
    // Only include approval info for admin operations
    private LocalDateTime approvedAt;
    private String approvedByName;
}