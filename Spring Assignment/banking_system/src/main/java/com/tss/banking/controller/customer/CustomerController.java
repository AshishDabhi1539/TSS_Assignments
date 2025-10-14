package com.tss.banking.controller.customer;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
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
<<<<<<< HEAD

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.dto.response.AccountStatementDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.service.AccountService;
import com.tss.banking.service.AccountStatementService;
import com.tss.banking.service.UserService;
import com.tss.banking.service.TransactionService;
=======
import org.springframework.web.multipart.MultipartFile;

import com.tss.banking.dto.request.AccountRequestDto;
import com.tss.banking.dto.request.InterBankTransferRequestDto;
import com.tss.banking.dto.request.TransactionRequestDto;
import com.tss.banking.dto.response.AccountResponseDto;
import com.tss.banking.dto.response.AccountStatementDto;
import com.tss.banking.dto.response.InterBankTransferResponseDto;
import com.tss.banking.dto.response.KYCDocumentResponseDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.dto.response.TransferRequestSummaryDto;
import com.tss.banking.dto.response.UserResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.security.SecurityUtils;
import com.tss.banking.service.AccountService;
import com.tss.banking.service.AccountStatementService;
import com.tss.banking.service.InterBankTransferService;
import com.tss.banking.service.KYCDocumentService;
import com.tss.banking.service.TransactionService;
import com.tss.banking.service.UserService;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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

<<<<<<< HEAD
    @GetMapping("/profile")
    @Operation(summary = "Get customer profile", description = "Get current customer profile information")
    public ResponseEntity<UserResponseDto> getUserProfile(Authentication authentication) {
        String email = authentication.getName();
=======
    @Autowired
    private KYCDocumentService kycDocumentService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InterBankTransferService interBankTransferService;

    @GetMapping("/profile")
    @Operation(summary = "Get customer profile", description = "Get current customer profile information")
    public ResponseEntity<UserResponseDto> getUserProfile(Authentication authentication) {
        String email = SecurityUtils.getCurrentUserEmail();
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        return ResponseEntity.ok(userService.getCurrentUserProfile(email));
    }

    @PostMapping("/accounts")
    @Operation(summary = "Create account", description = "Create a new account for the customer")
<<<<<<< HEAD
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto dto, Authentication authentication) {
        // Note: Customer ID should be extracted from authentication context in service layer
        // For now, the service layer will handle the customer association
        return ResponseEntity.ok(accountService.createAccount(dto));
=======
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto dto) {
        String email = SecurityUtils.getCurrentUserEmail();
        return ResponseEntity.ok(accountService.createAccountForCustomer(dto, email));
    }

    @GetMapping("/accounts")
    @Operation(summary = "Get customer accounts", description = "Get all accounts for the authenticated customer")
    public ResponseEntity<List<AccountResponseDto>> getCustomerAccounts() {
        String email = SecurityUtils.getCurrentUserEmail();
        return ResponseEntity.ok(accountService.getAccountsByCustomerEmail(email));
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @PostMapping("/transactions")
    @Operation(summary = "Create transaction", description = "Create a new transaction (DEBIT, CREDIT, or TRANSFER)")
<<<<<<< HEAD
    public ResponseEntity<TransactionResponseDto> createTransaction(@Valid @RequestBody TransactionRequestDto dto, Authentication authentication) {
        // Note: Account ownership validation should be implemented in service layer
        // For now, the service layer will handle the validation
        return ResponseEntity.ok(transactionService.createTransaction(dto));
=======
    public ResponseEntity<TransactionResponseDto> createTransaction(@Valid @RequestBody TransactionRequestDto dto) {
        String email = SecurityUtils.getCurrentUserEmail();
        return ResponseEntity.ok(transactionService.createTransactionForCustomer(dto, email));
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @GetMapping("/transactions")
    @Operation(summary = "Get transaction history", description = "Get paginated transaction history for the customer")
    public ResponseEntity<Page<TransactionResponseDto>> getTransactionHistory(
<<<<<<< HEAD
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Get user ID from authentication context - this would need to be implemented in service layer
        // For now, using a placeholder approach
        Pageable pageable = PageRequest.of(page, size);
        // Note: This method needs to be implemented in TransactionService
        // return ResponseEntity.ok(transactionService.getTransactionsByCustomerEmail(email, pageable));
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerId(1L, pageable)); // Placeholder
=======
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        String email = SecurityUtils.getCurrentUserEmail();
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerEmail(email, pageable));
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @GetMapping("/accounts/{accountId}/transactions")
    @Operation(summary = "Get account transactions", description = "Get paginated transactions for a specific account")
    public ResponseEntity<Page<TransactionResponseDto>> getAccountTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
<<<<<<< HEAD
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId, pageable));
=======
        String email = SecurityUtils.getCurrentUserEmail();
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionService.getTransactionsByAccountIdForCustomer(accountId, email, pageable));
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }

    @GetMapping("/accounts/{accountId}/statement")
    @Operation(summary = "Get account statement", description = "Generate account statement for a date range")
    public ResponseEntity<AccountStatementDto> getAccountStatement(
            @PathVariable Long accountId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {
<<<<<<< HEAD
        // Parse date strings to LocalDateTime - this would need proper date parsing
        // For now, using current time as placeholder
        return ResponseEntity.ok(accountStatementService.generateAccountStatement(accountId, 
            java.time.LocalDateTime.now().minusDays(30), java.time.LocalDateTime.now()));
=======
        String email = SecurityUtils.getCurrentUserEmail();
        return ResponseEntity.ok(accountStatementService.generateAccountStatementForCustomer(
            accountId, fromDate, toDate, email));
    }

    @PostMapping("/kyc/upload")
    @Operation(summary = "Upload KYC document", description = "Upload KYC document for the authenticated customer")
    public ResponseEntity<KYCDocumentResponseDto> uploadKYCDocument(
            @RequestParam String documentType,
            @RequestParam String documentNumber,
            @RequestParam String issuedBy,
            @RequestParam("file") MultipartFile file) {
        String email = SecurityUtils.getCurrentUserEmail();
        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        return ResponseEntity.ok(kycDocumentService.uploadKYCDocument(
                customer.getId(), documentType, documentNumber, issuedBy, file));
    }

    @GetMapping("/kyc")
    @Operation(summary = "Get customer KYC documents", description = "Get all KYC documents for the authenticated customer")
    public ResponseEntity<List<KYCDocumentResponseDto>> getCustomerKYCDocuments() {
        String email = SecurityUtils.getCurrentUserEmail();
        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        return ResponseEntity.ok(kycDocumentService.getKYCDocumentsByCustomerId(customer.getId()));
    }



    // Inter-Bank Transfer endpoints

    /**
     * Initiate inter-bank transfer request
     * @param accountId Source account ID
     * @param transferRequest Inter-bank transfer request details
     * @param authentication The authentication object
     * @return Transfer response with reference number
     */
    @PostMapping("/accounts/{accountId}/inter-bank-transfer")
    public ResponseEntity<InterBankTransferResponseDto> initiateInterBankTransfer(
            @PathVariable Integer accountId,
            @Valid @RequestBody InterBankTransferRequestDto transferRequest,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            User customerUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Customer user not found"));

            InterBankTransferResponseDto response = interBankTransferService.initiateTransfer(accountId, transferRequest, customerUser);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println("DEBUG: Inter-bank transfer error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("DEBUG: Inter-bank transfer unexpected error: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get customer's inter-bank transfer requests
     * @param authentication The authentication object
     * @return List of transfer requests
     */
    @GetMapping("/inter-bank-transfers")
    public ResponseEntity<List<TransferRequestSummaryDto>> getCustomerTransferRequests(Authentication authentication) {
        try {
            String email = authentication.getName();
            User customerUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Customer user not found"));

            List<TransferRequestSummaryDto> transfers = interBankTransferService.getCustomerTransferRequests(customerUser);
            return ResponseEntity.ok(transfers);
        } catch (RuntimeException e) {
            System.out.println("DEBUG: Get transfers error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("DEBUG: Get transfers unexpected error: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get transfer request details by reference number
     * @param transferReference Transfer reference number
     * @param authentication The authentication object
     * @return Transfer request details
     */
    @GetMapping("/inter-bank-transfers/{transferReference}")
    public ResponseEntity<TransferRequestSummaryDto> getTransferRequestByReference(
            @PathVariable String transferReference,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            User customerUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Customer user not found"));

            TransferRequestSummaryDto transfer = interBankTransferService.getTransferRequestByReference(transferReference, customerUser);
            return ResponseEntity.ok(transfer);
        } catch (RuntimeException e) {
            System.out.println("DEBUG: Get transfer by reference error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("DEBUG: Get transfer by reference unexpected error: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Debug endpoint to check shared database connection
     * @return List of banks in shared database
     */
    @GetMapping("/debug/banks")
    public ResponseEntity<String> debugBanks() {
        try {
            // This is just for debugging - remove in production
            return ResponseEntity.ok("Debug endpoint - check console logs");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }
}
