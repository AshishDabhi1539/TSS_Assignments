package com.tss.basics.debugging;

import java.util.Random;

public class Factorial {

	public static void main(String[] args) {
		Random random = new Random();
		int number = random.nextInt(6);
		System.out.println("Generated number : " + number);
		
		calculateFactorial(number);
	}

	private static void calculateFactorial(int number) {
		int factorial = 1;
		for(int i = 1; i <= number; i++) {
			factorial *= i;
		}
		System.out.println("Factorial : " + factorial);
		
	}
}
