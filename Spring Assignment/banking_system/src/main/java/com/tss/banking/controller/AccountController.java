package com.tss.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Account Management APIs")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @Operation(summary = "Create account", description = "Create a new bank account")
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto dto) {
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Retrieve account details by ID")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
}
