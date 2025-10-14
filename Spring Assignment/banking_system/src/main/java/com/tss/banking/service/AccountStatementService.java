package com.tss.banking.service;

import java.time.LocalDateTime;

import com.tss.banking.dto.response.AccountStatementDto;

public interface AccountStatementService {
    AccountStatementDto generateAccountStatement(Long accountId, LocalDateTime fromDate, LocalDateTime toDate);
<<<<<<< HEAD
=======
    AccountStatementDto generateAccountStatementForCustomer(Long accountId, String fromDate, String toDate, String customerEmail);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    AccountStatementDto generateMonthlyStatement(Long accountId, int year, int month);
}
