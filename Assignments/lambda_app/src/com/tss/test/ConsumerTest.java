package com.tss.test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerTest {

	/*
	 * public static void main(String[] args) { Consumer<Integer> object = (number)
	 * -> { int factorial = 1;
	 * 
	 * for(int i = 1; i <= number; i++) { factorial *= i; }
	 * System.out.println("Factorial of " + number + " is " + factorial);
	 * 
	 * }; object.accept(5); }
	 */

	public static void main(String[] args) {

		BiConsumer<Integer, Integer> add = (a, b) -> {
			int sum = a + b;
			System.out.println("Sum of " + a + " and " + b + " is: " + sum);
		};

		add.accept(10, 20); // Example usage
	}
}
