package com.tss.banking.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
<<<<<<< HEAD
import com.tss.banking.dto.request.InterBankTransferRequestDto;
import com.tss.banking.dto.response.InterBankTransferResponseDto;
import com.tss.banking.dto.response.TransferRequestSummaryDto;
import com.tss.banking.dto.request.TransferActionRequestDto;
=======

import com.tss.banking.dto.request.InterBankTransferRequestDto;
import com.tss.banking.dto.request.TransferActionRequestDto;
import com.tss.banking.dto.response.InterBankTransferResponseDto;
import com.tss.banking.dto.response.TransferRequestSummaryDto;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Transaction;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.TransactionType;
import com.tss.banking.entity.enums.TransactionStatus;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.TransactionRepository;
<<<<<<< HEAD
// Minimal local interface to compile
interface InterBankTransferService {
    InterBankTransferResponseDto initiateTransfer(Integer accountId, InterBankTransferRequestDto transferRequest, com.tss.banking.entity.User customerUser);
    java.util.List<TransferRequestSummaryDto> getPendingTransferRequests(com.tss.banking.entity.User adminUser);
    String approveTransferRequest(Integer requestId, TransferActionRequestDto actionRequest, com.tss.banking.entity.User adminUser);
    String rejectTransferRequest(Integer requestId, TransferActionRequestDto actionRequest, com.tss.banking.entity.User adminUser);
    String processRefund(java.util.Map<String, Object> refundRequest);
    java.util.List<TransferRequestSummaryDto> getCustomerTransferRequests(com.tss.banking.entity.User customerUser);
    TransferRequestSummaryDto getTransferRequestByReference(String transferReference, com.tss.banking.entity.User customerUser);
}
=======
import com.tss.banking.service.InterBankTransferService;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba

@Service
@Transactional
public class InterBankTransferServiceImpl implements InterBankTransferService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String SOURCE_BANK_CODE = "BANK002"; // DeepS Bank
<<<<<<< HEAD

    private static final String SHARED_DB_URL = System.getenv("SHARED_DB_URL");
    private static final String SHARED_DB_USERNAME = System.getenv("SHARED_DB_USERNAME");
    private static final String SHARED_DB_PASSWORD = System.getenv("SHARED_DB_PASSWORD");

    /**
     * Get connection to shared database
     */
    private Connection getSharedDatabaseConnection() throws SQLException {
        System.out.println("🌐 SHARED DATABASE: Creating manual connection to Aiven");
        return DriverManager.getConnection(SHARED_DB_URL, SHARED_DB_USERNAME, SHARED_DB_PASSWORD);
    }
    
=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    @Override
    public InterBankTransferResponseDto initiateTransfer(Integer accountId, InterBankTransferRequestDto transferRequest, User customerUser) {
        System.out.println("🏦 MAIN DATABASE: Validating account ownership for accountId: " + accountId);
        
        // Validate account ownership
        Account account = validateAccountOwnership(accountId, customerUser);
        
        System.out.println("🌐 SHARED DATABASE: Validating destination bank: " + transferRequest.getDestinationBankCode());
        
        // Validate destination bank using manual connection
        try (Connection conn = getSharedDatabaseConnection()) {
            String bankQuery = "SELECT bank_id, bank_code, bank_name, api_endpoint, status FROM bank_registry WHERE bank_code = ? AND status = 'ACTIVE'";
            try (PreparedStatement stmt = conn.prepareStatement(bankQuery)) {
                stmt.setString(1, transferRequest.getDestinationBankCode());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("Destination bank not found or not active");
                    }
                    // Bank found, continue
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to validate destination bank: " + e.getMessage());
        }
        
        // Validate amount
        BigDecimal amount = transferRequest.getAmount();
        BigDecimal previousBalance = account.getBalance();
        BigDecimal previousAvailableBalance = account.getBalance(); // Using balance as available balance
        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be greater than zero");
        }
        
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        
        // Deduct amount from source account
        account.setBalance(account.getBalance().subtract(amount));
        
        accountRepository.save(account);
        
        // Generate transfer reference
        String transferReference = generateTransferReference();
        
        System.out.println("🌐 SHARED DATABASE: Creating transfer request with reference: " + transferReference);
        
        // Create transfer request in shared database using manual connection
        try (Connection conn = getSharedDatabaseConnection()) {
            String insertQuery = """
                INSERT INTO transfer_requests 
                (source_bank_code, destination_bank_code, source_account_number, 
                 destination_account_number, destination_account_holder_name, 
                 destination_ifsc_code, amount, transfer_reference, status, remarks, created_date)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'PENDING', ?, NOW())
                """;
            
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, SOURCE_BANK_CODE);
                stmt.setString(2, transferRequest.getDestinationBankCode());
                stmt.setString(3, account.getAccountNumber());
                stmt.setString(4, transferRequest.getDestinationAccountNumber());
                stmt.setString(5, transferRequest.getDestinationAccountHolderName());
                stmt.setString(6, transferRequest.getDestinationIfscCode());
                stmt.setBigDecimal(7, amount);
                stmt.setString(8, transferReference);
                stmt.setString(9, transferRequest.getRemarks());
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new RuntimeException("Failed to create transfer request");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create transfer request: " + e.getMessage());
        }
        
        System.out.println("🏦 MAIN DATABASE: Creating transaction record");
        
        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setDescription("Inter-bank transfer to " + transferRequest.getDestinationBankCode() + " - " + transferRequest.getRemarks());
        transaction.setBalanceBefore(previousBalance);
        transaction.setBalanceAfter(account.getBalance());
        transaction.setReferenceNumber(transferReference);
<<<<<<< HEAD
        // Transaction entity has no from/to account number fields; store in description/reference only
=======
        transaction.setFromAccountNumber(account.getAccountNumber());
        transaction.setToAccountNumber(transferRequest.getDestinationAccountNumber());
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        transaction.setStatus(TransactionStatus.PENDING);
        
        transactionRepository.save(transaction);
        
        // Prepare response
        InterBankTransferResponseDto response = new InterBankTransferResponseDto();
        response.setTransferReference(transferReference);
        response.setAmount(amount);
        response.setDestinationBankCode(transferRequest.getDestinationBankCode());
        response.setDestinationAccountNumber(transferRequest.getDestinationAccountNumber());
        response.setDestinationAccountHolderName(transferRequest.getDestinationAccountHolderName());
        response.setDestinationIfscCode(transferRequest.getDestinationIfscCode());
        response.setRemarks(transferRequest.getRemarks());
        response.setStatus("PENDING");
        response.setTransferDate(LocalDateTime.now());
        response.setCreatedDate(LocalDateTime.now());
        response.setNewBalance(account.getBalance());
        response.setNewAvailableBalance(account.getBalance());
        response.setPreviousBalance(previousBalance);
        response.setPreviousAvailableBalance(previousAvailableBalance);
        
        return response;
    }
    
    @Override
    public List<TransferRequestSummaryDto> getPendingTransferRequests(User adminUser) {
        System.out.println("🌐 SHARED DATABASE: Fetching pending transfer requests");
        
        String query = """
            SELECT tr.request_id, tr.source_bank_code, tr.destination_bank_code, 
                   tr.source_account_number, tr.destination_account_number, 
                   tr.destination_account_holder_name, tr.destination_ifsc_code, 
                   tr.amount, tr.transfer_reference, tr.status, tr.remarks, tr.created_date,
                   br.bank_name as destination_bank_name
            FROM transfer_requests tr
            LEFT JOIN bank_registry br ON tr.destination_bank_code = br.bank_code
            WHERE tr.destination_bank_code = ? AND tr.status = 'PENDING'
            ORDER BY tr.created_date DESC
            """;
        
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, SOURCE_BANK_CODE);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();
                        row.put("transfer_id", rs.getLong("request_id"));
                        row.put("source_bank_code", rs.getString("source_bank_code"));
                        row.put("destination_bank_code", rs.getString("destination_bank_code"));
                        row.put("source_account_number", rs.getString("source_account_number"));
                        row.put("destination_account_number", rs.getString("destination_account_number"));
                        row.put("destination_account_holder_name", rs.getString("destination_account_holder_name"));
                        row.put("destination_ifsc_code", rs.getString("destination_ifsc_code"));
                        row.put("amount", rs.getBigDecimal("amount"));
                        row.put("transfer_reference", rs.getString("transfer_reference"));
                        row.put("status", rs.getString("status"));
                        row.put("remarks", rs.getString("remarks"));
                        row.put("created_date", rs.getTimestamp("created_date"));
                        row.put("destination_bank_name", rs.getString("destination_bank_name"));
                        results.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch pending transfer requests: " + e.getMessage());
        }
        
        return results.stream()
                .map(this::convertToTransferRequestSummaryDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public String approveTransferRequest(Integer requestId, TransferActionRequestDto actionRequest, User adminUser) {
        // Get transfer request from shared database
        String selectQuery = "SELECT * FROM transfer_requests WHERE request_id = ? AND destination_bank_code = ?";
        Map<String, Object> transferRequest = null;
        
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                stmt.setInt(1, requestId);
                stmt.setString(2, SOURCE_BANK_CODE);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("Transfer request not found");
                    }
                    transferRequest = new HashMap<>();
                    transferRequest.put("request_id", rs.getLong("request_id"));
                    transferRequest.put("source_bank_code", rs.getString("source_bank_code"));
                    transferRequest.put("destination_bank_code", rs.getString("destination_bank_code"));
                    transferRequest.put("source_account_number", rs.getString("source_account_number"));
                    transferRequest.put("destination_account_number", rs.getString("destination_account_number"));
                    transferRequest.put("destination_account_holder_name", rs.getString("destination_account_holder_name"));
                    transferRequest.put("destination_ifsc_code", rs.getString("destination_ifsc_code"));
                    transferRequest.put("amount", rs.getBigDecimal("amount"));
                    transferRequest.put("transfer_reference", rs.getString("transfer_reference"));
                    transferRequest.put("status", rs.getString("status"));
                    transferRequest.put("remarks", rs.getString("remarks"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch transfer request: " + e.getMessage());
        }
        
        // Check if already processed
        String status = (String) transferRequest.get("status");
        if (!"PENDING".equals(status)) {
            throw new RuntimeException("Transfer request is not pending");
        }
        
        // Find destination account in our bank
        String destinationAccountNumber = (String) transferRequest.get("destination_account_number");
        Account destinationAccount = accountRepository.findByAccountNumber(destinationAccountNumber)
                .orElseThrow(() -> new RuntimeException("Destination account not found in our bank"));
        
        // Verify IFSC code matches (using branch IFSC code)
        String destinationIfsc = (String) transferRequest.get("destination_ifsc_code");
        if (!destinationAccount.getBranch().getIfsc().equals(destinationIfsc)) {
            throw new RuntimeException("IFSC code mismatch for destination account");
        }
        
        // Verify account holder name matches
        User customer = destinationAccount.getCustomer();
        String fullName = customer.getFirstName() + " " + customer.getLastName();
        String expectedName = (String) transferRequest.get("destination_account_holder_name");
        if (!fullName.equalsIgnoreCase(expectedName)) {
            throw new RuntimeException("Account holder name mismatch");
        }
        
        // Credit money to destination account
        BigDecimal amount = (BigDecimal) transferRequest.get("amount");
        BigDecimal previousBalance = destinationAccount.getBalance();
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
        
        accountRepository.save(destinationAccount);
        
        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setAccount(destinationAccount);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setBalanceBefore(previousBalance);
        transaction.setBalanceAfter(destinationAccount.getBalance());
        transaction.setDescription("Inter-bank transfer from " + transferRequest.get("source_bank_code") + 
                                 " - " + transferRequest.get("source_account_number") + 
                                 " (Ref: " + transferRequest.get("transfer_reference") + ")");
        
        transactionRepository.save(transaction);
        
        // Update status in shared database
        String updateQuery = "UPDATE transfer_requests SET status = 'APPROVED', processed_date = NOW(), processed_by_admin = ? WHERE request_id = ?";
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setLong(1, adminUser.getId());
                stmt.setInt(2, requestId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update transfer request status: " + e.getMessage());
        }
        
        return "Transfer request approved successfully. Amount credited to destination account.";
    }
<<<<<<< HEAD

=======
    
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    @Override
    public String rejectTransferRequest(Integer requestId, TransferActionRequestDto actionRequest, User adminUser) {
        // Get transfer request from shared database
        String selectQuery = "SELECT * FROM transfer_requests WHERE request_id = ? AND destination_bank_code = ?";
        Map<String, Object> transferRequest = null;
<<<<<<< HEAD

=======
        
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                stmt.setInt(1, requestId);
                stmt.setString(2, SOURCE_BANK_CODE);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("Transfer request not found");
                    }
                    transferRequest = new HashMap<>();
                    transferRequest.put("request_id", rs.getLong("request_id"));
                    transferRequest.put("source_bank_code", rs.getString("source_bank_code"));
                    transferRequest.put("destination_bank_code", rs.getString("destination_bank_code"));
                    transferRequest.put("source_account_number", rs.getString("source_account_number"));
                    transferRequest.put("destination_account_number", rs.getString("destination_account_number"));
                    transferRequest.put("destination_account_holder_name", rs.getString("destination_account_holder_name"));
                    transferRequest.put("destination_ifsc_code", rs.getString("destination_ifsc_code"));
                    transferRequest.put("amount", rs.getBigDecimal("amount"));
                    transferRequest.put("transfer_reference", rs.getString("transfer_reference"));
                    transferRequest.put("status", rs.getString("status"));
                    transferRequest.put("remarks", rs.getString("remarks"));
<<<<<<< HEAD
                    // transferRequest.put("rejection_reason", actionRequest.getReason());
=======
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch transfer request: " + e.getMessage());
        }
<<<<<<< HEAD

=======
        
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        // Check if already processed
        String status = (String) transferRequest.get("status");
        if (!"PENDING".equals(status)) {
            throw new RuntimeException("Transfer request is not pending");
        }
<<<<<<< HEAD

        // Update status in shared database
        String updateQuery = "UPDATE transfer_requests SET status = 'REJECTED', processed_date = NOW(), processed_by_admin = ?, rejection_reason = ? WHERE request_id = ?";
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setLong(1, adminUser.getId());
                stmt.setString(2, actionRequest.getReason());
                stmt.setInt(3, requestId);
=======
        
        // Update status in shared database
        String updateQuery = "UPDATE transfer_requests SET status = 'REJECTED', processed_date = NOW(), processed_by_admin = ? WHERE request_id = ?";
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setLong(1, adminUser.getId());
                stmt.setInt(2, requestId);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update transfer request status: " + e.getMessage());
        }
<<<<<<< HEAD

        // Notify source bank to refund the amount
        notifySourceBankToRefund(transferRequest);

        return "Transfer request rejected. Source bank has been notified to refund the amount.";
    }

=======
        
        // Notify source bank to refund the amount
        notifySourceBankToRefund(transferRequest);
        
        return "Transfer request rejected. Source bank has been notified to refund the amount.";
    }
    
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    @Override
    public String processRefund(Map<String, Object> refundRequest) {
        System.out.println("🏦 MAIN DATABASE: Processing refund for transfer reference: " + refundRequest.get("transferReference"));
        
        // Get transfer request from shared database
        String selectQuery = "SELECT * FROM transfer_requests WHERE transfer_reference = ?";
        Map<String, Object> transferRequest = null;
        
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                stmt.setString(1, (String) refundRequest.get("transferReference"));
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("Transfer request not found");
                    }
                    transferRequest = new HashMap<>();
                    transferRequest.put("request_id", rs.getLong("request_id"));
                    transferRequest.put("source_bank_code", rs.getString("source_bank_code"));
                    transferRequest.put("destination_bank_code", rs.getString("destination_bank_code"));
                    transferRequest.put("source_account_number", rs.getString("source_account_number"));
                    transferRequest.put("destination_account_number", rs.getString("destination_account_number"));
                    transferRequest.put("destination_account_holder_name", rs.getString("destination_account_holder_name"));
                    transferRequest.put("destination_ifsc_code", rs.getString("destination_ifsc_code"));
                    transferRequest.put("amount", rs.getBigDecimal("amount"));
                    transferRequest.put("transfer_reference", rs.getString("transfer_reference"));
                    transferRequest.put("status", rs.getString("status"));
                    transferRequest.put("remarks", rs.getString("remarks"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch transfer request: " + e.getMessage());
        }
        
        // Check if already refunded
        String status = (String) transferRequest.get("status");
        if ("PROCESSED".equals(status)) {
            throw new RuntimeException("Transfer has already been refunded");
        }
        
        // Find source account and process refund
        String sourceAccountNumber = (String) transferRequest.get("source_account_number");
        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        
        // Refund amount to source account
        BigDecimal refundAmount = (BigDecimal) transferRequest.get("amount");
        sourceAccount.setBalance(sourceAccount.getBalance().add(refundAmount));
        accountRepository.save(sourceAccount);
        
        // Create refund transaction record
        Transaction refundTransaction = new Transaction();
        refundTransaction.setAccount(sourceAccount);
        refundTransaction.setType(TransactionType.CREDIT);
        refundTransaction.setAmount(refundAmount);
        refundTransaction.setDescription("Inter-bank transfer refund - " + refundRequest.get("transferReference"));
        refundTransaction.setBalanceBefore(sourceAccount.getBalance().subtract(refundAmount));
        refundTransaction.setBalanceAfter(sourceAccount.getBalance());
<<<<<<< HEAD
        // Transaction entity does not track from/to numbers; description and reference suffice
=======
        refundTransaction.setFromAccountNumber(sourceAccountNumber);
        refundTransaction.setToAccountNumber(sourceAccountNumber);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
        refundTransaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(refundTransaction);
        // Update status in shared database
        String updateQuery = "UPDATE transfer_requests SET status = 'PROCESSED', processed_date = NOW() WHERE transfer_reference = ?";
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, (String) refundRequest.get("transferReference"));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update transfer request status: " + e.getMessage());
        }
        
        return "Refund processed successfully. Amount credited back to source account.";
    }
    
    @Override
    public List<TransferRequestSummaryDto> getCustomerTransferRequests(User customerUser) {
        System.out.println("🏦 MAIN DATABASE: Getting customer account numbers");
        
        // Get customer's account numbers from main database
        List<Account> customerAccounts = accountRepository.findByCustomer(customerUser);
        if (customerAccounts.isEmpty()) {
            return new ArrayList<>(); // No accounts found
        }
        
        // Extract account numbers
        List<String> accountNumbers = customerAccounts.stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList());
        
        System.out.println("🌐 SHARED DATABASE: Fetching customer transfer requests for accounts: " + accountNumbers);
        
        // Build query with account numbers
        String placeholders = accountNumbers.stream().map(s -> "?").collect(Collectors.joining(","));
        String query = """
            SELECT tr.request_id, tr.source_bank_code, tr.destination_bank_code, 
                   tr.source_account_number, tr.destination_account_number, 
                   tr.destination_account_holder_name, tr.destination_ifsc_code, 
                   tr.amount, tr.transfer_reference, tr.status, tr.remarks, tr.created_date,
                   br.bank_name as destination_bank_name
            FROM transfer_requests tr
            LEFT JOIN bank_registry br ON tr.destination_bank_code = br.bank_code
            WHERE tr.source_bank_code = ? AND tr.source_account_number IN (%s)
            ORDER BY tr.created_date DESC
            """.formatted(placeholders);
        
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, SOURCE_BANK_CODE);
                // Set account numbers
                for (int i = 0; i < accountNumbers.size(); i++) {
                    stmt.setString(i + 2, accountNumbers.get(i));
                }
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();
                        row.put("transfer_id", rs.getLong("request_id"));
                        row.put("source_bank_code", rs.getString("source_bank_code"));
                        row.put("destination_bank_code", rs.getString("destination_bank_code"));
                        row.put("source_account_number", rs.getString("source_account_number"));
                        row.put("destination_account_number", rs.getString("destination_account_number"));
                        row.put("destination_account_holder_name", rs.getString("destination_account_holder_name"));
                        row.put("destination_ifsc_code", rs.getString("destination_ifsc_code"));
                        row.put("amount", rs.getBigDecimal("amount"));
                        row.put("transfer_reference", rs.getString("transfer_reference"));
                        row.put("status", rs.getString("status"));
                        row.put("remarks", rs.getString("remarks"));
                        row.put("created_date", rs.getTimestamp("created_date"));
                        row.put("destination_bank_name", rs.getString("destination_bank_name"));
                        results.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch customer transfer requests: " + e.getMessage());
        }
        
        return results.stream()
                .map(this::convertToTransferRequestSummaryDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public TransferRequestSummaryDto getTransferRequestByReference(String transferReference, User customerUser) {
        System.out.println("🏦 MAIN DATABASE: Getting customer account numbers");
        
        // Get customer's account numbers from main database
        List<Account> customerAccounts = accountRepository.findByCustomer(customerUser);
        if (customerAccounts.isEmpty()) {
            throw new RuntimeException("No accounts found for customer");
        }
        
        // Extract account numbers
        List<String> accountNumbers = customerAccounts.stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList());
        
        System.out.println("🌐 SHARED DATABASE: Fetching transfer request by reference: " + transferReference);
        
        // Build query with account numbers
        String placeholders = accountNumbers.stream().map(s -> "?").collect(Collectors.joining(","));
        String query = """
            SELECT tr.request_id, tr.source_bank_code, tr.destination_bank_code, 
                   tr.source_account_number, tr.destination_account_number, 
                   tr.destination_account_holder_name, tr.destination_ifsc_code, 
                   tr.amount, tr.transfer_reference, tr.status, tr.remarks, tr.created_date,
                   br.bank_name as destination_bank_name
            FROM transfer_requests tr
            LEFT JOIN bank_registry br ON tr.destination_bank_code = br.bank_code
            WHERE tr.transfer_reference = ? AND tr.source_bank_code = ? AND tr.source_account_number IN (%s)
            """.formatted(placeholders);
        
        try (Connection conn = getSharedDatabaseConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, transferReference);
                stmt.setString(2, SOURCE_BANK_CODE);
                // Set account numbers
                for (int i = 0; i < accountNumbers.size(); i++) {
                    stmt.setString(i + 3, accountNumbers.get(i));
                }
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("Transfer request not found");
                    }
                    
                    Map<String, Object> row = new HashMap<>();
                    row.put("transfer_id", rs.getLong("request_id"));
                    row.put("source_bank_code", rs.getString("source_bank_code"));
                    row.put("destination_bank_code", rs.getString("destination_bank_code"));
                    row.put("source_account_number", rs.getString("source_account_number"));
                    row.put("destination_account_number", rs.getString("destination_account_number"));
                    row.put("destination_account_holder_name", rs.getString("destination_account_holder_name"));
                    row.put("destination_ifsc_code", rs.getString("destination_ifsc_code"));
                    row.put("amount", rs.getBigDecimal("amount"));
                    row.put("transfer_reference", rs.getString("transfer_reference"));
                    row.put("status", rs.getString("status"));
                    row.put("remarks", rs.getString("remarks"));
                    row.put("created_date", rs.getTimestamp("created_date"));
                    row.put("destination_bank_name", rs.getString("destination_bank_name"));
                    
                    return convertToTransferRequestSummaryDto(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch transfer request: " + e.getMessage());
        }
    }
    
    private Account validateAccountOwnership(Integer accountId, User customerUser) {
        Account account = accountRepository.findById(accountId.longValue())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        if (!account.getCustomer().getId().equals(customerUser.getId())) {
            throw new RuntimeException("Account does not belong to the authenticated user");
        }
        
        return account;
    }
    
    private String generateTransferReference() {
        return "IBT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private void notifySourceBankToRefund(Map<String, Object> transferRequest) {
        try {
            // Get source bank details from shared database
            String bankQuery = "SELECT api_endpoint FROM bank_registry WHERE bank_code = ?";
            String apiEndpoint = null;
            
            try (Connection conn = getSharedDatabaseConnection()) {
                try (PreparedStatement stmt = conn.prepareStatement(bankQuery)) {
                    stmt.setString(1, (String) transferRequest.get("source_bank_code"));
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            apiEndpoint = rs.getString("api_endpoint");
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to get source bank API endpoint: " + e.getMessage());
            }
            
            if (apiEndpoint == null) {
                throw new RuntimeException("Source bank API endpoint not found");
            }
            
            // Prepare refund request
            Map<String, Object> refundRequest = new HashMap<>();
            refundRequest.put("transferReference", transferRequest.get("transfer_reference"));
            refundRequest.put("amount", transferRequest.get("amount"));
            refundRequest.put("reason", "Transfer rejected by destination bank");
            
            // Send refund request to source bank
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(refundRequest, headers);
            
            String refundUrl = apiEndpoint + "/api/admin/inter-bank/process-refund";
            restTemplate.postForObject(refundUrl, request, String.class);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to notify source bank for refund: " + e.getMessage());
        }
    }
    
    private TransferRequestSummaryDto convertToTransferRequestSummaryDto(Map<String, Object> row) {
        TransferRequestSummaryDto dto = new TransferRequestSummaryDto();
        dto.setTransferId(((Number) row.get("transfer_id")).longValue());
        dto.setSourceBankCode((String) row.get("source_bank_code"));
        dto.setDestinationBankCode((String) row.get("destination_bank_code"));
        dto.setSourceAccountNumber((String) row.get("source_account_number"));
        dto.setDestinationAccountNumber((String) row.get("destination_account_number"));
        dto.setDestinationAccountHolderName((String) row.get("destination_account_holder_name"));
        dto.setDestinationIfscCode((String) row.get("destination_ifsc_code"));
        dto.setAmount((BigDecimal) row.get("amount"));
        dto.setTransferReference((String) row.get("transfer_reference"));
        dto.setStatus((String) row.get("status"));
        dto.setRemarks((String) row.get("remarks"));
        
        // Convert Timestamp to LocalDateTime
        Timestamp timestamp = (Timestamp) row.get("created_date");
        if (timestamp != null) {
            dto.setCreatedDate(timestamp.toLocalDateTime());
        }
        
        dto.setDestinationBankName((String) row.get("destination_bank_name"));
        
        return dto;
    }
}