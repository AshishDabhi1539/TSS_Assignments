package com.tss.util;

@SuppressWarnings("serial")
public class AppException extends Exception {
	public AppException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Application Error : " + super.getMessage();
	}
}
