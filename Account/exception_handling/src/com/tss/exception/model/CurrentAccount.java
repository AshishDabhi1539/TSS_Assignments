package com.tss.exception.model;

import com.tss.exception.NegativeAmountException;
import com.tss.exception.OverdraftLimitReachException;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String name, double balance, double overdraftLimit) throws NegativeAmountException {
        super(accountNumber, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void credit(double amount) throws NegativeAmountException {
        if (amount <= 0)
            throw new NegativeAmountException("Amount must be positive.");
        setBalance(getBalance() + amount);
        System.out.println("Amount credited to Current Account.");
    }

    @Override
    public void debit(double amount) throws NegativeAmountException, OverdraftLimitReachException {
        if (amount <= 0)
            throw new NegativeAmountException("Amount must be positive.");
        if (getBalance() - amount < -overdraftLimit)
            throw new OverdraftLimitReachException("Overdraft limit exceeded.");
        setBalance(getBalance() - amount);
        System.out.println("Debited Successfully.");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}
