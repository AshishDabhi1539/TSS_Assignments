package com.tss.banking.exception;

public class BankApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BankApiException(String message) {
		super(message);
	}

	public BankApiException(String message, Throwable cause) {
		super(message, cause);
	}
}