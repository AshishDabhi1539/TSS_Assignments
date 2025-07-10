package com.tss.model;

public class FixedDepositPrinter {
	public void printDeposit(FixedDepositClass deposit, FixedDepositCalculator calculator) {
		System.out.println("Account No: " + deposit.getAccountNumber());
		System.out.println("Name      : " + deposit.getName());
		System.out.println("Principal : " + deposit.getPrincipal());
		System.out.println("Duration  : " + deposit.getDuration() + " Years");
		System.out.println("Interest Rate: " + deposit.getInterest().getInterestRate() + "%");
		System.out.println("Simple Interest: " + calculator.calculateSimpleInterest(deposit));
		System.out.println();
	}
}
