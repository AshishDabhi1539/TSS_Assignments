package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BranchResponseDto {
    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private Long bankId;
}