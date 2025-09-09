package com.tss.banking.controller.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.dto.response.AccountStatementDto;
import com.tss.banking.dto.response.CustomerResponseDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.service.AccountService;
import com.tss.banking.service.AccountStatementService;
import com.tss.banking.service.CustomerService;
import com.tss.banking.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountStatementService accountStatementService;

    @GetMapping("/profile")
    public ResponseEntity<CustomerResponseDto> getCustomerProfile(@RequestParam Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountRequestDto dto) {
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponseDto> createTransaction(@Valid @RequestBody TransactionRequestDto dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/transactions")
    public ResponseEntity<Page<TransactionResponseDto>> getTransactionHistory(
            @RequestParam Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerId(customerId, pageable));
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<Page<TransactionResponseDto>> getAccountTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId, pageable));
    }

    @GetMapping("/accounts/{accountId}/statement")
    public ResponseEntity<AccountStatementDto> generateAccountStatement(
            @PathVariable Long accountId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        LocalDateTime from = LocalDateTime.parse(fromDate + "T00:00:00");
        LocalDateTime to = LocalDateTime.parse(toDate + "T23:59:59");
        return ResponseEntity.ok(accountStatementService.generateAccountStatement(accountId, from, to));
    }

    @GetMapping("/accounts/{accountId}/statement/monthly")
    public ResponseEntity<AccountStatementDto> generateMonthlyStatement(
            @PathVariable Long accountId,
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(accountStatementService.generateMonthlyStatement(accountId, year, month));
    }
}