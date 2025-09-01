package com.tss.jpa.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class StudentApiException extends RuntimeException {
	private String message;
	public String getMessage() {
		return message;
	}
}
