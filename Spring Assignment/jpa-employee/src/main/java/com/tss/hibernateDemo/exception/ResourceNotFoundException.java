package com.tss.hibernateDemo.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
