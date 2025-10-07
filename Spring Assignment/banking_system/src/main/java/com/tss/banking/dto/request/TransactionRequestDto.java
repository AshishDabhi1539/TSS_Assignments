package com.tss.banking.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    
    @NotBlank(message = "Account number is required")
    @Size(max = 64, message = "Account number cannot exceed 64 characters")
    private String accountNumber;
    
    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "^(DEBIT|CREDIT|TRANSFER|DEPOSIT|WITHDRAWAL)$", 
             message = "Transaction type must be DEBIT, CREDIT, TRANSFER, DEPOSIT, or WITHDRAWAL")
    private String type;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    @Size(max = 512, message = "Description cannot exceed 512 characters")
    private String description;
    
    // For TRANSFER transactions
    @Size(max = 64, message = "To account number cannot exceed 64 characters")
    private String toAccountNumber;
    
    // Auto-generated idempotency key for transaction safety
    @Size(max = 100, message = "Idempotency key cannot exceed 100 characters")
    private String idempotencyKey;
}