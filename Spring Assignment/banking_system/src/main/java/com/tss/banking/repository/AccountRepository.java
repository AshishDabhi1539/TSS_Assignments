package com.tss.banking.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.tss.banking.entity.Account;
import com.tss.banking.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByCustomer(User customer);
=======
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
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}