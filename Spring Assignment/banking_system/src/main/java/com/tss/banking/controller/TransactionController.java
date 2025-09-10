package com.tss.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Transaction Management APIs")
@PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Create transaction", description = "Create a new transaction (DEBIT, CREDIT, or TRANSFER)")
    public ResponseEntity<TransactionResponseDto> createTransaction(@Valid @RequestBody TransactionRequestDto dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Retrieve transaction details by ID")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Get account transactions", description = "Get paginated transactions for an account")
    public ResponseEntity<Page<TransactionResponseDto>> getAccountTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId, pageable));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get customer transactions", description = "Get paginated transactions for a customer")
    public ResponseEntity<Page<TransactionResponseDto>> getCustomerTransactions(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerId(customerId, pageable));
    }
}
