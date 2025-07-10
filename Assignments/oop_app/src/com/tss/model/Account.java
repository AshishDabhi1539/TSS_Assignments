package com.tss.model;

public class Account {
	private int accountId, accountNumber;
	private String holderName;
	private AccountType accountType;
	private double accountBalance;

	public Account() {
		accountId = 0;
		accountNumber = 0;
		holderName = "";
		accountBalance = 0;
	}

	public Account(int accountId, int accountNumber, String holderName, double accountBalance, AccountType accountType) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
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
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", holderName=" + holderName
				+ ", accountType=" + accountType + ", accountBalance=" + accountBalance + "]";
	}
}
