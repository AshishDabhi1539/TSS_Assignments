package com.tss.test;

import com.tss.model.DbLogger;
import com.tss.model.FileLogger;
import com.tss.model.ILogger;
import com.tss.model.TaxCalculator;

public class TaxCalculatorTest {

	public static void main(String[] args) {
		ILogger dbLogger = new DbLogger();
		TaxCalculator taxWithDbLogger = new TaxCalculator(dbLogger);
		taxWithDbLogger.calculateTax(10000, 0);
		
		System.out.println();
		
		ILogger fileLogger = new FileLogger();
		TaxCalculator taxWithFileLogger = new TaxCalculator(fileLogger);
		taxWithFileLogger.calculateTax(10000, 0);
	}

}
