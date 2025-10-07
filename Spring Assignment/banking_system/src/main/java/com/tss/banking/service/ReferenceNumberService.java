package com.tss.banking.service;

public interface ReferenceNumberService {
    String generateTransactionReference();
    String generateAccountReference();
    String generateTransferReference();
    String generateIdempotencyKey(String prefix, Object... params);
    boolean isValidReference(String reference);
}
