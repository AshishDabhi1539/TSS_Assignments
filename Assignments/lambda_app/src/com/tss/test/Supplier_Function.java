package com.tss.test;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Supplier_Function {

	public static void main(String[] args) {
		Supplier<Integer> randomNumber = () -> {
			Random random = new Random();
			return random.nextInt();
		};
		
		Function<Integer, Integer> factorial = (number) -> {
			int factorail = 1;
			
			for(int i = 1; i <= number; i++) {
				factorail *= i;
			}
			return factorail;
		};
		
		BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        
		System.out.println(randomNumber.get());
		
		int result = factorial.apply(5);
		System.out.println("Factorial of 5 is " + result);
		
		int sum = addition.apply(10, 20);
        System.out.println("Sum of 10 and 20 is: " + sum);
	}

}
