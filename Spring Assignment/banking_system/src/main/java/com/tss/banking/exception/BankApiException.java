package com.tss.banking.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class BankApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}
}