package com.tss.model;

import java.io.Serializable;

public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	private double threshold;
	private double amount;
	
	public Discount(double threshold, double amount) {
		super();
		this.threshold = threshold;
		this.amount = amount;
	}
	
	public double applyDiscount(double total) {
		return total > threshold ? amount : 0;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
