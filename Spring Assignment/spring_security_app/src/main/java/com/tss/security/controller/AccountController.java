package com.tss.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.security.dto.AccountDto;
import com.tss.security.dto.AccountResponseDto;
import com.tss.security.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDto> addAccount(@Valid @RequestBody AccountDto accountDto) {
        AccountResponseDto savedAccount = accountService.addAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> readAllAccounts() {
        List<AccountResponseDto> accounts = accountService.readAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> readParticularAccount(@PathVariable Long id) {
        AccountResponseDto account = accountService.readParticularAccount(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<AccountResponseDto> disableAccount(@PathVariable Long id) {
        AccountResponseDto disabledAccount = accountService.disableAccount(id);
        return ResponseEntity.ok(disabledAccount);
    }
}
