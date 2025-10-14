package com.tss.banking.dto.response;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
=======
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
<<<<<<< HEAD
    private String email;
    private String phone;
    private String address;
    private String status;
    private String role;
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}