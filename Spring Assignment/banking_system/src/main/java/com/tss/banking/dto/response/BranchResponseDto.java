package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponseDto {
    private Long id;
    private String name;
    private String code;
    private String ifsc;
    private String address;
    private String contactNumber;
    private String email;
    private boolean isActive;
    
    // Bank information
    private Long bankId;
    private String bankName;
    private String bankCode;
}