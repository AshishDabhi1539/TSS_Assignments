package com.tss.policy.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class PolicyApiException extends RuntimeException {
    private String message;
    public String getMessage() {
        return message;
    }
}
