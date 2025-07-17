package com.tss.exception;

import java.io.Serial;

public class AppException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public AppException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Application Error: " + super.getMessage();
    }
}