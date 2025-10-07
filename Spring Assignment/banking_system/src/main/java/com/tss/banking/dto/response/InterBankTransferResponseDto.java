package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterBankTransferResponseDto {
    
    private boolean success;
    private String message;
    private String transferReference;
    private BigDecimal amount;
    private BigDecimal previousBalance;
    private BigDecimal previousAvailableBalance;
    private BigDecimal newBalance;
    private BigDecimal newAvailableBalance;
    private LocalDateTime transferDate;
    private LocalDateTime createdDate;
    private String destinationBankCode;
    private String destinationAccountNumber;
    private String destinationAccountHolderName;
    private String destinationIfscCode;
    private String remarks;
    private String status;
}
