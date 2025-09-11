package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BankRequestDto {
    
    @NotBlank(message = "Bank name is required")
    @Size(min = 2, max = 128, message = "Bank name must be between 2 and 128 characters")
    private String name;
    
    @NotBlank(message = "Address is required")
    @Size(max = 512, message = "Address cannot exceed 512 characters")
    private String address;
    
    private String code; // optional, auto-generate if null
    
    private String currency; // default INR
    
    private String country = "IN"; // default to India
    
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Please provide a valid contact number (10-15 digits)")
    private String contactNumber; // mandatory
    
    // Optional overrides for default branch on bank creation
    private String defaultBranchName;
    private String defaultBranchCode;
    private String defaultBranchIfsc;
}