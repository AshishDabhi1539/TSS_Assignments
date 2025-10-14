package com.tss.banking.dto.request;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchRequestDto {
    private String name;
    private String code; // optional, auto-generate if null
    private String ifsc; // optional, auto-generate if null
    private String address;
    private String contactNumber;
    private Long bankId;
=======
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequestDto {
    
    @NotBlank(message = "Branch name is required")
    @Size(min = 2, max = 128, message = "Branch name must be between 2 and 128 characters")
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
    
    @NotBlank(message = "Branch code is required")
    @Size(min = 2, max = 32, message = "Branch code must be between 2 and 32 characters")
    private String code;
    
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "IFSC must be in format: 4 letters + 0 + 6 alphanumeric")
    private String ifsc;
    
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Please provide a valid contact number")
    private String contactNumber;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Size(max = 128, message = "Email cannot exceed 128 characters")
    private String email;
    
    @NotNull(message = "Bank ID is required")
    private Long bankId;
    
    private Boolean isActive = true;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}