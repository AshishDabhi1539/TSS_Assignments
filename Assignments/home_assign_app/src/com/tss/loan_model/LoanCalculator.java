package com.tss.loan_model;

import java.util.function.Function;

public class LoanCalculator implements Function<CustomerClass, Double> {

	@Override
	public Double apply(CustomerClass customer) {
		return customer.getIncome() * (customer.getCreditScore() / 1000.0);
	}

}
