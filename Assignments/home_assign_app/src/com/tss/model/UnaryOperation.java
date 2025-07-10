package com.tss.model;

import java.util.Scanner;

public class UnaryOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		int a = 10, b = -5, number = 5;
		boolean isLoggedIn = false;

		System.out.println("\n--- Unary Operators ---");
		System.out.println("Original a :" + a);
		System.out.println("Unary Plus (+a): " + (+a));
		System.out.println("Unary Minus (-a): " + (-a) + "\n");

		System.out.println("Prefix Increment (++a): " + (++a));
		System.out.println("Postfix Increment (a++): " + (a++));
		System.out.println("After Postfix Increment: " + a + "\n");
		
		System.out.println("Original b :" + b);
		System.out.println("Prefix Decrement (--b): " + (--b));
		System.out.println("Postfix Decrement (b--): " + (b--));
		System.out.println("After Postfix Decrement: " + b + "\n");
		
		System.out.println("Original Boolean :" + isLoggedIn);
		System.out.println("Logical NOT (!isLoggedIn): " + (!isLoggedIn) + "\n");
		
		System.out.println("Original Number :" + number);
		System.out.println("Bitwise Complement (~number): " + (~number));
		System.out.println("If the binary represents an unsigned integer, it equals 4294967290.\nIf it represents a signed 32-bit integer (two's complement), it equals -6.");
	}

}
