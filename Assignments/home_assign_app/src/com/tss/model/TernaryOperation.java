package com.tss.model;

import java.util.Scanner;

public class TernaryOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		System.out.println("\n-- Ternary Operator --");
		System.out.print("Enter 1st number : ");
		int number1 = scanner.nextInt();
		
		System.out.print("Enter 2nd number : ");
		int number2 = scanner.nextInt();
		
		String result = (number1 > number2) ? "1st Number is greater" : (number1 < number2) ? "2nd Number is greater" : "Both numbers are equal";
		
		System.out.println("Ternary result : " + result);
	}

}
