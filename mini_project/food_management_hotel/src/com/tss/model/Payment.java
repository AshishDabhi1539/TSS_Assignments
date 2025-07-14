package com.tss.model;

public class Payment {
	public enum Mode {
		CASH, UPI
	}

	private Mode mode;
	private double amount;

	public Payment(Mode mode, double amount) {
		super();
		this.mode = mode;
		this.amount = amount;
	}

	public Mode getMode() {
		return mode;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Paid " + amount + " rupees via " + mode;
	}

}
