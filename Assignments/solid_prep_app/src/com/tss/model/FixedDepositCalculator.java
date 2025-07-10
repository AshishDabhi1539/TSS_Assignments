package com.tss.model;

public class FixedDepositCalculator {
	public double calculateSimpleInterest(FixedDepositClass deposit) {
		return (deposit.getPrincipal() * deposit.getDuration() * deposit.getInterest().getInterestRate()) / 100;
	}
}
