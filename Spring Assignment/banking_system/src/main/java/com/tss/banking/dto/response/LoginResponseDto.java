package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private String type = "Bearer";
    private String email;
    private String role;
    private Long userId;
    private String message;
    
    public LoginResponseDto(String token, String email, String role, Long userId) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.userId = userId;
        this.message = "Login successful";
    }
}
