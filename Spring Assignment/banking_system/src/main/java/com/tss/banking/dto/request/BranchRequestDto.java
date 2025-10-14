package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchRequestDto {
    private String name;
    private String code; // optional, auto-generate if null
    private String ifsc; // optional, auto-generate if null
    private String address;
    private String contactNumber;
    private Long bankId;
}