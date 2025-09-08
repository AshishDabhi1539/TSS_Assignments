package com.tss.banking.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.CustomerRequestDto;
import com.tss.banking.dto.response.CustomerResponseDto;
import com.tss.banking.service.CustomerService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDto> registerCustomer(@RequestBody CustomerRequestDto dto) {
        return ResponseEntity.ok(customerService.registerCustomer(dto));
    }
}