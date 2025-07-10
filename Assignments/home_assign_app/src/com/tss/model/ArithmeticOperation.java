package com.tss.model;

import java.util.Scanner;

public class ArithmeticOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		int x = 20, y = 5;
        System.out.println("\n--- Arithmetic Operators ---");
        System.out.println("x : " + x + " & y : " + y);
        System.out.println("Addition (x + y): " + (x + y));
        System.out.println("Subtraction (x - y): " + (x - y));
        System.out.println("Multiplication (x * y): " + (x * y));
        System.out.println("Division (x / y): " + (x / y));
        System.out.println("Modulus (x % y): " + (x % y));
	}

}
