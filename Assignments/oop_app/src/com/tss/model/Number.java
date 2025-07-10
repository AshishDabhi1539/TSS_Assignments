package com.tss.model;

public class Number {
	Integer number;	

	public Number(Integer number1) {
		this.number = number1;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number1) {
		this.number = number1;
	}
	
	@Override
	public String toString() {
		return number.toString();
	}
		
}
