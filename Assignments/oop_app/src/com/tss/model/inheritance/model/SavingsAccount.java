package com.tss.model.inheritance.model;

public class SavingsAccount extends Account {

	private double minimumBalance;	
	
	public SavingsAccount(int accountNumber, String name, double balance, double minimumBalance) {
		super(accountNumber, name, balance);
		this.minimumBalance = minimumBalance;
	}

	@Override
    public void credit(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("Amount credited to Savings Account.");
    }
	
	@Override
    public void debit(double amount) {
        if (getBalance() - amount >= minimumBalance) {
            setBalance(getBalance() - amount);
            System.out.println("Debited Successfully...");
        } else {
            System.out.println("Debit Not Possible...");
        }
    }
	
	@Override
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Minimum Balance Required : " + minimumBalance);
	}
}
