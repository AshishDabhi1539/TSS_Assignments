package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestSummaryDto {
    
    private Long transferId;
    private Integer requestId;
    private String sourceBankCode;
    private String destinationBankCode;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String destinationAccountHolderName;
    private String destinationIfscCode;
    private BigDecimal amount;
    private String transferReference;
    private String status;
    private String remarks;
    private LocalDateTime createdDate;
    private LocalDateTime processedDate;
    private String rejectionReason;
    private String destinationBankName;
}
