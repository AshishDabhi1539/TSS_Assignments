package com.tss.model;

public class TaxCalculator {
	private ILogger logger;

	public TaxCalculator(ILogger logger) {
		super();
		this.logger = logger;
	}
	
	public int calculateTax(int amount, int rate) {
		int tax = 0;
        try {
            tax = amount / rate;
            System.out.println("Tax: " + tax);
        } catch (Exception exception) {
            logger.log("Divide by zero error");
        }
        return tax;
	}
}
