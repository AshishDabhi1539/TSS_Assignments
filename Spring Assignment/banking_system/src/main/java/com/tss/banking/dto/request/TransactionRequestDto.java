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
public class TransactionRequestDto {
    
    @NotBlank(message = "Account number is required")
    @Size(max = 20, message = "Account number cannot exceed 20 characters")
    private String accountNumber;
    
    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "^(DEBIT|CREDIT|TRANSFER)$", message = "Transaction type must be DEBIT, CREDIT, or TRANSFER")
    private String type;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private long amount;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    // For TRANSFER transactions
    @Size(max = 20, message = "To account number cannot exceed 20 characters")
    private String toAccountNumber;
    
    // Auto-generated idempotency key for transaction safety
    private String idempotencyKey;
}