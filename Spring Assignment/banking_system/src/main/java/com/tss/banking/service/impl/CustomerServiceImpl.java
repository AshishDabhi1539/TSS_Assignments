package com.tss.banking.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.CustomerRequestDto;
import com.tss.banking.dto.response.CustomerResponseDto;
import com.tss.banking.entity.Customer;
import com.tss.banking.entity.enums.CustomerStatus;
import com.tss.banking.entity.enums.RoleType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.CustomerRepository;
import com.tss.banking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerResponseDto registerCustomer(CustomerRequestDto dto) {
        if ("SUPERADMIN".equalsIgnoreCase(dto.getRole())) {
            throw new BankApiException("SuperAdmin registration is not allowed via this process.");
        }
        if (!("CUSTOMER".equalsIgnoreCase(dto.getRole()) || "ADMIN".equalsIgnoreCase(dto.getRole()))) {
            throw new BankApiException("Invalid role for registration: " + dto.getRole());
        }

        Customer customer = mapper.map(dto, Customer.class);
        customer.setStatus(CustomerStatus.PENDING);
        try {
            customer.setRole(RoleType.valueOf(dto.getRole().toUpperCase()));
        } catch (Exception ex) {
            throw new BankApiException("Invalid role: " + dto.getRole());
        }
        Customer savedCustomer = customerRepo.save(customer);
        return mapper.map(savedCustomer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Customer not found with ID: " + id));
        return mapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto approveCustomer(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new BankApiException("Customer not found with ID: " + id));
        if (customer.getStatus() != CustomerStatus.PENDING) {
            throw new BankApiException("Customer is not in PENDING status for approval.");
        }
        customer.setStatus(CustomerStatus.VERIFIED);
        Customer updatedCustomer = customerRepo.save(customer);
        return mapper.map(updatedCustomer, CustomerResponseDto.class);
    }
}