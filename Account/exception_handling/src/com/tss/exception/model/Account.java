package com.tss.exception.model;

import com.tss.exception.NegativeAmountException;

public abstract class Account {
    private int accountNumber;
    private String name;
    private double balance;

    public Account(int accountNumber, String name, double balance) throws NegativeAmountException {
        this.accountNumber = accountNumber;
        this.name = name;
        if (balance < 0)
            throw new NegativeAmountException("Initial balance cannot be negative.");
        this.balance = balance;
    }

    public abstract void debit(double amount) throws Exception;
    public abstract void credit(double amount) throws Exception;

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }
}
