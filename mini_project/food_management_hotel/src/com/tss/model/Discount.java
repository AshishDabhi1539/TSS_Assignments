package com.tss.model;

public class Discount {
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
}
