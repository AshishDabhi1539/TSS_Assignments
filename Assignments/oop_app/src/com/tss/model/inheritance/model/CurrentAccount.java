package com.tss.model.inheritance.model;

public class CurrentAccount extends Account {

	private double overdraftLimit;

	public CurrentAccount(int accountNumber, String name, double balance, double overdraftLimit) {
		super(accountNumber, name, balance);
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	public void credit(double amount) {
		setBalance(getBalance() + amount);
		System.out.println("Amount credited to Current Account.");
	}

	@Override
	public void debit(double amount) {
		if (getBalance() - amount >= -overdraftLimit) {
			setBalance(getBalance() - amount);
			System.out.println("Debited Successfully.");
		} else {
			System.out.println("Debit Not Possible.");
		}
	}

	public void displayDetails() {
		super.displayDetails();
		System.out.println("Overdrafted Limit : " + overdraftLimit);
	}
}
