package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    
<<<<<<< HEAD
    @NotBlank(message = "Email/Username is required")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
=======
    @NotBlank(message = "Email is required")
    @Size(max = 128, message = "Email cannot exceed 128 characters")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    private String password;
}
