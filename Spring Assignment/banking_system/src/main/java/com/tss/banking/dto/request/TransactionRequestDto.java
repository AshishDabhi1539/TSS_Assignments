package com.tss.banking.dto.request;

<<<<<<< HEAD
=======
import java.math.BigDecimal;

>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    
    @NotNull(message = "Account ID is required")
    private Long accountId;
    
    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "^(DEBIT|CREDIT|TRANSFER)$", message = "Transaction type must be DEBIT, CREDIT, or TRANSFER")
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    private String type;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
<<<<<<< HEAD
    private long amount;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    // For TRANSFER transactions
    private Long toAccountId;
    
=======
    private BigDecimal amount;
    
    @Size(max = 512, message = "Description cannot exceed 512 characters")
    private String description;
    
    // For TRANSFER transactions
    @Size(max = 64, message = "To account number cannot exceed 64 characters")
    private String toAccountNumber;
    
    // Auto-generated idempotency key for transaction safety
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    @Size(max = 100, message = "Idempotency key cannot exceed 100 characters")
    private String idempotencyKey;
}