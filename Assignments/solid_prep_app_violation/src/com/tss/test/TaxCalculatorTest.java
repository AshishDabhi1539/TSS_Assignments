package com.tss.test;

import com.tss.mode.TaxCalculator;

public class TaxCalculatorTest {

	public static void main(String[] args) {
		TaxCalculator taxCalculator = new TaxCalculator();
		taxCalculator.calculateTax(10000, 0);
	}

}
