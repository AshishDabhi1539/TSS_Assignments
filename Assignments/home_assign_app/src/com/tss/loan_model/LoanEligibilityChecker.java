package com.tss.loan_model;

import java.util.function.Predicate;

public class LoanEligibilityChecker implements Predicate<CustomerClass> {

	@Override
	public boolean test(CustomerClass customer) {
		return customer.getAge() >= 21 && customer.getIncome() >= 25000 && customer.getCreditScore() >= 650;
	}

}
