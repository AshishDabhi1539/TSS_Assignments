package com.tss.banking.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

<<<<<<< HEAD
public class KYCDocumentResponseDto {
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KYCDocumentResponseDto {
    
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    private Long id;
    private Long customerId;
    private String documentType;
    private String documentNumberMasked;
    private String status;
    private String issuedBy;
    private LocalDate expiryDate;
    private String fileRef;
<<<<<<< HEAD
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public String getDocumentNumberMasked() { return documentNumberMasked; }
    public void setDocumentNumberMasked(String documentNumberMasked) { this.documentNumberMasked = documentNumberMasked; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getIssuedBy() { return issuedBy; }
    public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getFileRef() { return fileRef; }
    public void setFileRef(String fileRef) { this.fileRef = fileRef; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}


=======
    private String fileUrl;
    private LocalDateTime createdAt;
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
