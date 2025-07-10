package com.tss.exception;

public class OverdraftLimitReachException extends Exception {
	public OverdraftLimitReachException(String message) {
        super(message);
    }
}
