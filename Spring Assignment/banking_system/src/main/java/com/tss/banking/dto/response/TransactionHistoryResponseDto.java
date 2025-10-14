package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryResponseDto {
    private List<TransactionDetailDto> transactions;
    private PaginationDto pagination;
    private TransactionSummaryDto summary;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionDetailDto {
        private Long id;
        private String type;
        private BigDecimal amount;
        private BigDecimal feeAmount;
        private BigDecimal balanceAfter;
        private LocalDateTime date;
        private String description;
        private String status;
        private String referenceNumber;
        
        // For transfers
        private String recipientAccountNumber;
        private String recipientName;
        
        // For inter-bank transfers
        private String recipientBankName;
        private String recipientIfsc;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaginationDto {
        private int currentPage;
        private int totalPages;
        private long totalElements;
        private int pageSize;
        private boolean hasNext;
        private boolean hasPrevious;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionSummaryDto {
        private BigDecimal totalCredits;
        private BigDecimal totalDebits;
        private BigDecimal netAmount;
        private int totalTransactions;
        private LocalDateTime periodStart;
        private LocalDateTime periodEnd;
    }
}
