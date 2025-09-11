package com.tss.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.Account;
import com.tss.banking.entity.Branch;
import com.tss.banking.entity.User;
import com.tss.banking.entity.enums.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(Long customerId);
    List<Account> findByCustomer(User customer);
    List<Account> findByBranchId(Long branchId);
    boolean existsByCustomerAndBranchAndAccountType(User customer, Branch branch, AccountType accountType);
}