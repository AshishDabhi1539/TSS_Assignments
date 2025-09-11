package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchRequestDto {
    
    @NotBlank(message = "Branch name is required")
    @Size(min = 2, max = 128, message = "Branch name must be between 2 and 128 characters")
    private String name;
    
    // Auto-generated based on branch name
    private String code; // auto-generate based on name
    
    // Auto-generated based on bank code + branch name
    private String ifsc; // auto-generate based on bank + branch
    
    @NotBlank(message = "Address is required")
    @Size(max = 512, message = "Address cannot exceed 512 characters")
    private String address;
    
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Please provide a valid contact number (10-15 digits)")
    private String contactNumber;
    
    @NotNull(message = "Bank ID is required")
    private Long bankId;
}