package com.tss.banking.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.response.CustomerResponseDto;
import com.tss.banking.service.CustomerService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @PutMapping("/approve-customer/{id}")
    public ResponseEntity<CustomerResponseDto> approveCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.approveCustomer(id));
    }
}