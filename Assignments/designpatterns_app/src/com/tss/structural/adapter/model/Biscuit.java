package com.tss.structural.adapter.model;

public class Biscuit implements IItems {
	private String name;
	private double price;
	
	public Biscuit(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String getItemName() {
		return name;
	}

	@Override
	public double getItemPrice() {
		return price;
	}

}
