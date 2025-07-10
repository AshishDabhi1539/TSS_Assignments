package com.tss.loan_model;

import java.util.function.Consumer;
import java.util.function.Function;

public class ApprovalPrinter implements Consumer<CustomerClass> {
	private final Function<CustomerClass, Double> calculator;

    public ApprovalPrinter(Function<CustomerClass, Double> calculator) {
        this.calculator = calculator;
    }

	@Override
	public void accept(CustomerClass customer) {
		double eligibleAmount = calculator.apply(customer);
        System.out.println("Loan Approved for " + customer.getName());
        System.out.println("Eligible Amount: " + eligibleAmount + "\n");
	}

}
