package com.tss.mode;

public class TaxCalculator {
	private DbLogger dbLogger = new DbLogger();
	
	public int calculateTax(int amount, int rate) {
		int tax = 0;
		try {
			tax = amount / rate;
			System.out.println("Tax : " + tax);
		}catch (Exception exception) {
			dbLogger.log("Divide by 0 Error");
		}
		return tax;
	}
}
