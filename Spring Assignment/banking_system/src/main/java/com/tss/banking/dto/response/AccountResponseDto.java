package com.tss.banking.dto.response;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
=======
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
@AllArgsConstructor
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private String accountType;
<<<<<<< HEAD
    private double balance;
    private String status;
    private Long customerId;
    private Long branchId;
=======
    private BigDecimal balance;
    private String status;
    private String currency;
    private BigDecimal interestRate;
    private BigDecimal minBalance;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    
    // Customer info (no sensitive data)
    private Long customerId;
    private String customerName;
    
    // Branch info
    private Long branchId;
    private String branchName;
    private String branchCode;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}