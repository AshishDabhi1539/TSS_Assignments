package com.tss.banking.dto.request;

<<<<<<< HEAD
public class KYCDocumentRequestDto {
}


=======
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KYCDocumentRequestDto {
    
    // Optional for customer operations (derived from auth), required for admin operations
    private Long customerId;
    
    @NotBlank(message = "Document type is required")
    @Pattern(regexp = "^(PAN|AADHAAR|PASSPORT|DRIVING_LICENSE|VOTER_ID)$", 
             message = "Document type must be PAN, AADHAAR, PASSPORT, DRIVING_LICENSE, or VOTER_ID")
    private String documentType;
    
    @NotBlank(message = "Document number is required")
    @Size(min = 5, max = 64, message = "Document number must be between 5 and 64 characters")
    private String documentNumber;
    
    @Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$", message = "Status must be PENDING, APPROVED, or REJECTED")
    private String status = "PENDING";
    
    @Size(max = 128, message = "Issued by cannot exceed 128 characters")
    private String issuedBy;
    
    private LocalDate expiryDate;
    
    @Size(max = 512, message = "File reference cannot exceed 512 characters")
    private String fileRef;
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
