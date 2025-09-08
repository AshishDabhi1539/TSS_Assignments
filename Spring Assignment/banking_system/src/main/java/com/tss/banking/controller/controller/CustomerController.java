package com.tss.banking.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.dto.response.CustomerResponseDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.service.AccountService;
import com.tss.banking.service.CustomerService;
import com.tss.banking.service.TransactionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerProfile(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountRequestDto dto) {
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }
}