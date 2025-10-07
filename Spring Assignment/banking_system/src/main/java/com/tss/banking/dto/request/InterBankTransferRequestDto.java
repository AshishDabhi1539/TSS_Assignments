package com.tss.banking.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterBankTransferRequestDto {
    
    @NotBlank(message = "Destination bank code is required")
    private String destinationBankCode;
    
    @NotBlank(message = "Destination account number is required")
    private String destinationAccountNumber;
    
    @NotBlank(message = "Destination account holder name is required")
    private String destinationAccountHolderName;
    
    @NotBlank(message = "Destination IFSC code is required")
    private String destinationIfscCode;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    @NotBlank(message = "Remarks are required")
    @Size(min = 5, max = 255, message = "Remarks must be between 5 and 255 characters")
    private String remarks;
}
