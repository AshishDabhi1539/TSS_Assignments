package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String status;
    private String role;
}