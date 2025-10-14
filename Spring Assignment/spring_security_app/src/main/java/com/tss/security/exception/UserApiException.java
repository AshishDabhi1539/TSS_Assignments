package com.tss.security.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	
	// Constructor with just message (defaults to BAD_REQUEST)
	public UserApiException(String message) {
		this.message = message;
		this.status = HttpStatus.BAD_REQUEST;
	}
}
