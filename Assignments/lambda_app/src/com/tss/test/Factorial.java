package com.tss.test;

import com.tss.model.IFactorial;

public class Factorial {

	public static void main(String[] args) {
		
		IFactorial fact = (n) -> {
			int factorial = 1;
			
			for(int i = 1; i <= n; i++) {
				factorial *= i;
			}
			System.out.println("Factorial of " + n + " is " + factorial);
		};
		
		fact.factorial(5);
		
	}

}
