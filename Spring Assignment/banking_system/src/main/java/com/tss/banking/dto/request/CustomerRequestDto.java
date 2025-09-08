package com.tss.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role; // e.g., CUSTOMER, ADMIN (SUPERADMIN not allowed via registration)
}