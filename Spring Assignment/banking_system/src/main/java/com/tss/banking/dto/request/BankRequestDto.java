package com.tss.banking.dto.request;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BankRequestDto {
    private String name;
    private String address;
    private String code; // optional, auto-generate if null
    private String currency; // default INR
    private String country; // optional
    // Optional overrides for default branch on bank creation
    private String defaultBranchName;
    private String defaultBranchCode;
=======
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDto {
    
    @NotBlank(message = "Bank name is required")
    @Size(min = 2, max = 128, message = "Bank name must be between 2 and 128 characters")
    private String name;
    
    // Address fields for registration
    @NotBlank(message = "Address line 1 is required")
    @Size(max = 255, message = "Address line 1 cannot exceed 255 characters")
    private String addressLine1;
    
    @NotBlank(message = "Address line 2 is required")
    @Size(max = 255, message = "Address line 2 cannot exceed 255 characters")
    private String addressLine2;
    
    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;
    
    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State cannot exceed 100 characters")
    private String state;
    
    @NotBlank(message = "Postal code is required")
    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    private String postalCode;
    
    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country cannot exceed 100 characters")
    private String country = "India";
    
    @NotBlank(message = "Bank code is required")
    @Size(min = 2, max = 32, message = "Bank code must be between 2 and 32 characters")
    private String code;
    
    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter ISO code (e.g., USD, EUR, INR)")
    private String currency = "INR"; // default to India
    
    // Optional overrides for default branch on bank creation
    @Size(max = 128, message = "Default branch name cannot exceed 128 characters")
    private String defaultBranchName;
    
    @Size(max = 32, message = "Default branch code cannot exceed 32 characters")
    private String defaultBranchCode;
    
    @Size(max = 20, message = "Default branch IFSC cannot exceed 20 characters")
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    private String defaultBranchIfsc;
}