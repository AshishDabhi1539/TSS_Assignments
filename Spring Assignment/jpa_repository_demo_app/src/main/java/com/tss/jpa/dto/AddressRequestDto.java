package com.tss.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressRequestDto {
    @NotBlank(message = "Street is required")
    @Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;

    @NotBlank(message = "Postal code is required")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    private String pinCode; // Changed from pinCode to match entity

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must not exceed 50 characters")
    private String country;
}