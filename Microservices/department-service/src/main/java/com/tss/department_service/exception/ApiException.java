package com.tss.department_service.exception;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
