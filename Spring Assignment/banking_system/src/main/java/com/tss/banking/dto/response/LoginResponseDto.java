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
    private Long customerId;
    private String message;
    
    public LoginResponseDto(String token, String email, String role, Long customerId) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.customerId = customerId;
        this.message = "Login successful";
    }
}
