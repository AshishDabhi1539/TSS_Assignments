package com.tss.exception;

public class AgeNotValidException extends Exception {
	private int age;

	public AgeNotValidException(int age) {
		this.age = age;
	}
	
	public String getMessage()
	{
		return "You are not eligible cause you are "+age + " years old";
	}
}
