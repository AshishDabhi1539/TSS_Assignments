package com.tss.model;

public class Account1 {
	
	private int accountId;
	private static int accId = 1;
	private String holderName, accountNumber;
	private AccountType accountType;
	private double accountBalance;

	public Account1() {
		this.accountId = accId++;
		accountNumber = "";
		holderName = "";
		accountBalance = 0;
	}

	public Account1(String accountNumber, String holderName, double accountBalance, AccountType accountType) {
		this.accountId = accId++;
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public int getAccountId() {
		return accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "[accountId=" + accountId + ", accountNumber=" + accountNumber + ", holderName=" + holderName
				+ ", accountType=" + accountType + ", accountBalance=" + accountBalance + "]";
	}
}
