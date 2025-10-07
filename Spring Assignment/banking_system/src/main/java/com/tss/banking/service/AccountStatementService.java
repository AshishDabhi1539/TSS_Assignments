package com.tss.banking.service;

import java.time.LocalDateTime;

import com.tss.banking.dto.response.AccountStatementDto;

public interface AccountStatementService {
    AccountStatementDto generateAccountStatement(Long accountId, LocalDateTime fromDate, LocalDateTime toDate);
    AccountStatementDto generateAccountStatementForCustomer(Long accountId, String fromDate, String toDate, String customerEmail);
    AccountStatementDto generateMonthlyStatement(Long accountId, int year, int month);
}
