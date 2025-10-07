package com.tss.banking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.response.AccountStatementDto;
import com.tss.banking.dto.response.TransactionResponseDto;
import com.tss.banking.entity.Account;
import com.tss.banking.entity.Transaction;
import com.tss.banking.entity.enums.TransactionType;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AccountRepository;
import com.tss.banking.repository.TransactionRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.AccountStatementService;
import com.tss.banking.entity.User;
import java.time.format.DateTimeFormatter;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AccountStatementDto generateAccountStatement(Long accountId, LocalDateTime fromDate, LocalDateTime toDate) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BankApiException("Account not found with ID: " + accountId));

        List<Transaction> transactions = transactionRepository.findByAccountIdAndDateBetweenOrderByDateDesc(
                accountId, fromDate, toDate);

        return buildAccountStatement(account, transactions, fromDate, toDate);
    }

    @Override
    public AccountStatementDto generateAccountStatementForCustomer(Long accountId, String fromDate, String toDate, String customerEmail) {
        // Validate that the account belongs to the customer
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BankApiException("Account not found with ID: " + accountId));
        
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new BankApiException("Customer not found"));
        
        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new BankApiException("Access denied: You can only view statements for your own accounts");
        }
        
        // Parse date strings to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromDateTime = LocalDateTime.parse(fromDate + "T00:00:00");
        LocalDateTime toDateTime = LocalDateTime.parse(toDate + "T23:59:59");
        
        return generateAccountStatement(accountId, fromDateTime, toDateTime);
    }

    @Override
    public AccountStatementDto generateMonthlyStatement(Long accountId, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime fromDate = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime toDate = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        return generateAccountStatement(accountId, fromDate, toDate);
    }

    private AccountStatementDto buildAccountStatement(Account account, List<Transaction> transactions, 
                                                    LocalDateTime fromDate, LocalDateTime toDate) {
        AccountStatementDto statement = new AccountStatementDto();
        
        // Account details
        statement.setAccountId(account.getId());
        statement.setAccountNumber(account.getAccountNumber());
        statement.setAccountType(account.getAccountType().name());
        statement.setCustomerName(account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName());
        statement.setCustomerEmail(account.getCustomer().getEmail());
        statement.setCurrentBalance(account.getBalance());
        statement.setStatementGeneratedAt(LocalDateTime.now());
        statement.setFromDate(fromDate);
        statement.setToDate(toDate);

        // Transaction details
        List<TransactionResponseDto> transactionDtos = transactions.stream()
                .map(transaction -> mapper.map(transaction, TransactionResponseDto.class))
                .toList();
        statement.setTransactions(transactionDtos);

        // Calculate totals
        BigDecimal totalCredits = transactions.stream()
                .filter(t -> t.getType() == TransactionType.CREDIT)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDebits = transactions.stream()
                .filter(t -> t.getType() == TransactionType.DEBIT)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        statement.setTotalCredits(totalCredits);
        statement.setTotalDebits(totalDebits);
        statement.setTransactionCount(transactions.size());

        return statement;
    }
}
