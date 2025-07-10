package com.tss.model.inheritance.model;

public abstract class Account {
	private int accountNumber;
	private String name;
	private double balance;
	
	public Account(int accountNumber, String name, double balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.setBalance(balance);
	}
	
	public abstract void debit(double amount);
	
	public abstract void credit(double amount);
	
	public void displayDetails() {
		System.out.println("Account Number : "+accountNumber);
		System.out.println("Name : "+name);
		System.out.println("Balance : "+getBalance());
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
