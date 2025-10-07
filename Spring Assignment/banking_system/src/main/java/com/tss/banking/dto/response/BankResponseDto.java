package com.tss.banking.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankResponseDto {
    private Long id;
    private String name;
    private String address;
    private String code;
    private String currency;
    private String country;
    private LocalDateTime createdAt;
    private int totalBranches;
}