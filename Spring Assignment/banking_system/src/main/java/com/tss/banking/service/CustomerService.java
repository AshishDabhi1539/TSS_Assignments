package com.tss.banking.service;

import com.tss.banking.dto.request.CustomerRequestDto;
import com.tss.banking.dto.response.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto registerCustomer(CustomerRequestDto dto);
    CustomerResponseDto getCustomerById(Long id);
    CustomerResponseDto approveCustomer(Long id);
}