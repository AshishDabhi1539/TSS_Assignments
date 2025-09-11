package com.tss.banking.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.dto.response.AccountStatementDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.AccountService;
import com.tss.banking.service.AccountStatementService;
import com.tss.banking.service.KYCDocumentService;
import com.tss.banking.service.UserService;
import com.tss.banking.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer Management APIs")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountStatementService accountStatementService;

    @Autowired
    private KYCDocumentService kycDocumentService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    @Operation(summary = "Get customer profile", description = "Get current customer profile information")
    public ResponseEntity<UserResponseDto> getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getCurrentUserProfile(email));
    }

    @PostMapping("/accounts")
    @Operation(summary = "Create account", description = "Create a new account for the customer")
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto dto, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(accountService.createAccountForCustomer(dto, email));
    }

    @GetMapping("/accounts")
    @Operation(summary = "Get customer accounts", description = "Get all accounts for the authenticated customer")
    public ResponseEntity<List<AccountResponseDto>> getCustomerAccounts(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(accountService.getAccountsByCustomerEmail(email));
    }

    @PostMapping("/transactions")
    @Operation(summary = "Create transaction", description = "Create a new transaction (DEBIT, CREDIT, or TRANSFER)")
    public ResponseEntity<TransactionResponseDto> createTransaction(@Valid @RequestBody TransactionRequestDto dto, Authentication authentication) {
        // Note: Account ownership validation should be implemented in service layer
        // For now, the service layer will handle the validation
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/transactions")
    @Operation(summary = "Get transaction history", description = "Get paginated transaction history for the customer")
    public ResponseEntity<Page<TransactionResponseDto>> getTransactionHistory(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Get user ID from authentication context - this would need to be implemented in service layer
        // For now, using a placeholder approach
        Pageable pageable = PageRequest.of(page, size);
        // Note: This method needs to be implemented in TransactionService
        // return ResponseEntity.ok(transactionService.getTransactionsByCustomerEmail(email, pageable));
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerId(1L, pageable)); // Placeholder
    }

    @GetMapping("/accounts/{accountId}/transactions")
    @Operation(summary = "Get account transactions", description = "Get paginated transactions for a specific account")
    public ResponseEntity<Page<TransactionResponseDto>> getAccountTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId, pageable));
    }

    @GetMapping("/accounts/{accountId}/statement")
    @Operation(summary = "Get account statement", description = "Generate account statement for a date range")
    public ResponseEntity<AccountStatementDto> getAccountStatement(
            @PathVariable Long accountId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        // Parse date strings to LocalDateTime - this would need proper date parsing
        // For now, using current time as placeholder
        return ResponseEntity.ok(accountStatementService.generateAccountStatement(accountId, 
            java.time.LocalDateTime.now().minusDays(30), java.time.LocalDateTime.now()));
    }

    @PostMapping("/kyc/upload")
    @Operation(summary = "Upload KYC document", description = "Upload KYC document for the authenticated customer")
    public ResponseEntity<KYCDocumentResponseDto> uploadKYCDocument(
            @RequestParam String documentType,
            @RequestParam String documentNumber,
            @RequestParam String issuedBy,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        String email = authentication.getName();
        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        return ResponseEntity.ok(kycDocumentService.uploadKYCDocument(
                customer.getId(), documentType, documentNumber, issuedBy, file));
    }

    @GetMapping("/kyc")
    @Operation(summary = "Get customer KYC documents", description = "Get all KYC documents for the authenticated customer")
    public ResponseEntity<List<KYCDocumentResponseDto>> getCustomerKYCDocuments(Authentication authentication) {
        String email = authentication.getName();
        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        return ResponseEntity.ok(kycDocumentService.getKYCDocumentsByCustomerId(customer.getId()));
    }
}
