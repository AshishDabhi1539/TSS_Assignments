package com.tss.banking.service.impl;

import org.springframework.stereotype.Service;
import com.tss.banking.service.ReferenceNumberService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ReferenceNumberServiceImpl implements ReferenceNumberService {

    private static final String TRANSACTION_PREFIX = "TXN";
    private static final String ACCOUNT_PREFIX = "ACC";
    private static final String TRANSFER_PREFIX = "TRF";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public String generateTransactionReference() {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        String randomSuffix = String.format("%04d", ThreadLocalRandom.current().nextInt(1000, 9999));
        return TRANSACTION_PREFIX + timestamp + randomSuffix;
    }

    @Override
    public String generateAccountReference() {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        String randomSuffix = String.format("%04d", ThreadLocalRandom.current().nextInt(1000, 9999));
        return ACCOUNT_PREFIX + timestamp + randomSuffix;
    }

    @Override
    public String generateTransferReference() {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        String randomSuffix = String.format("%04d", ThreadLocalRandom.current().nextInt(1000, 9999));
        return TRANSFER_PREFIX + timestamp + randomSuffix;
    }

    @Override
    public String generateIdempotencyKey(String prefix, Object... params) {
        StringBuilder keyBuilder = new StringBuilder(prefix);
        keyBuilder.append("_");
        
        for (Object param : params) {
            keyBuilder.append(param.toString()).append("_");
        }
        
        keyBuilder.append(System.currentTimeMillis());
        
        // Generate hash to ensure uniqueness and reasonable length
        return keyBuilder.toString().replaceAll("[^a-zA-Z0-9_]", "").toUpperCase();
    }

    @Override
    public boolean isValidReference(String reference) {
        if (reference == null || reference.length() < 10) {
            return false;
        }
        
        return reference.matches("^(TXN|ACC|TRF)\\d{14}\\d{4}$");
    }
}
