package com.tss.model;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String username;
	private String password;
	private String upiId;
	private String upiPin;
	private double discountThreshold;

	public Customer(int id, String name, String username, String password, String upiId, String upiPin) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.upiId = upiId;
		this.upiPin = upiPin;
		this.discountThreshold = 500.0;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getUpiId() {
		return upiId;
	}

	public String getUpiPin() {
		return upiPin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDiscountThreshold(double discountThreshold) {
		this.discountThreshold = discountThreshold;
	}

	public double getDiscountThreshold() {
		return discountThreshold;
	}

	public boolean authenticate(String user, String pass) {
		return this.username.equals(user) && this.password.equals(pass);
	}
}