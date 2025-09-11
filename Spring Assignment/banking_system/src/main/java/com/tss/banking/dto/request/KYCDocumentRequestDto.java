package com.tss.banking.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KYCDocumentRequestDto {
    
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    @NotBlank(message = "Document type is required")
    @Size(max = 32, message = "Document type must not exceed 32 characters")
    private String documentType;
    
    @NotBlank(message = "Document number is required")
    @Size(max = 64, message = "Document number must not exceed 64 characters")
    private String documentNumber;
    
    @Size(max = 32, message = "Status must not exceed 32 characters")
    private String status;
    
    private String issuedBy;
    
    private LocalDate expiryDate;
    
    private String fileRef;
}
