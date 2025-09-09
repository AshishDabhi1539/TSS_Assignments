package com.tss.banking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tss.banking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByIdempotencyKey(String idempotencyKey);
    
    List<Transaction> findByAccountIdOrderByDateDesc(Long accountId);
    
    Page<Transaction> findByAccountIdOrderByDateDesc(Long accountId, Pageable pageable);
    
    @Query("SELECT t FROM Transaction t WHERE t.account.customer.id = :customerId ORDER BY t.date DESC")
    Page<Transaction> findByCustomerIdOrderByDateDesc(@Param("customerId") Long customerId, Pageable pageable);
    
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.date BETWEEN :fromDate AND :toDate ORDER BY t.date DESC")
    List<Transaction> findByAccountIdAndDateBetweenOrderByDateDesc(@Param("accountId") Long accountId, 
                                                                  @Param("fromDate") LocalDateTime fromDate, 
                                                                  @Param("toDate") LocalDateTime toDate);
}