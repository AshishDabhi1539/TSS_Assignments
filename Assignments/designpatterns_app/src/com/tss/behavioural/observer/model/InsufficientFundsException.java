package com.tss.behavioural.observer.model;

@SuppressWarnings("serial")
public class InsufficientFundsException extends RuntimeException {
	public InsufficientFundsException(String message) {
        super(message);
    }
}
	