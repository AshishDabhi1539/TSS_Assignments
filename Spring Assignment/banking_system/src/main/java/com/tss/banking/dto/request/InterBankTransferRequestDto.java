package com.tss.banking.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class InterBankTransferRequestDto {
    
    @NotBlank(message = "From account number is required")
    @Size(max = 20, message = "From account number cannot exceed 20 characters")
    private String fromAccountNumber;
    
    @NotBlank(message = "To bank IFSC is required")
    @Size(max = 20, message = "Bank IFSC cannot exceed 20 characters")
    private String toBankIfsc;
    
    @NotBlank(message = "To account number is required")
    @Size(max = 20, message = "To account number cannot exceed 20 characters")
    private String toAccountNumber;
    
    @NotBlank(message = "Beneficiary name is required")
    @Size(max = 100, message = "Beneficiary name cannot exceed 100 characters")
    private String beneficiaryName;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private long amount;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    @NotBlank(message = "Transaction PIN is required")
    @Pattern(regexp = "^[0-9]{4,6}$", message = "Transaction PIN must be 4-6 digits")
    private String transactionPin;
    
    // Auto-generated idempotency key for transaction safety
    private String idempotencyKey;
}
