package com.tss.banking.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KYCDocumentResponseDto {
    
    private Long id;
    private Long customerId;
    private String documentType;
    private String documentNumberMasked;
    private String status;
    private String issuedBy;
    private LocalDate expiryDate;
    private String fileRef;
    private String fileUrl;
    private LocalDateTime createdAt;
}
