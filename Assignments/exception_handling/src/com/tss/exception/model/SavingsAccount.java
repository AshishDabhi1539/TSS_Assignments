package com.tss.exception.model;

import com.tss.exception.MinimumBalanceViolationException;
import com.tss.exception.NegativeAmountException;

public class SavingsAccount extends Account {
    private double minimumBalance;

    public SavingsAccount(int accountNumber, String name, double balance, double minimumBalance) throws NegativeAmountException {
        super(accountNumber, name, balance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void credit(double amount) throws NegativeAmountException {
        if (amount <= 0)
            throw new NegativeAmountException("Amount must be positive.");
        setBalance(getBalance() + amount);
        System.out.println("Amount credited to Savings Account.");
    }

    @Override
    public void debit(double amount) throws NegativeAmountException, MinimumBalanceViolationException {
        if (amount <= 0)
            throw new NegativeAmountException("Amount must be positive.");
        if (getBalance() - amount < minimumBalance)
            throw new MinimumBalanceViolationException("Minimum balance requirement not met.");
        setBalance(getBalance() - amount);
        System.out.println("Debited Successfully...");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Minimum Balance Required: " + minimumBalance);
    }
}
