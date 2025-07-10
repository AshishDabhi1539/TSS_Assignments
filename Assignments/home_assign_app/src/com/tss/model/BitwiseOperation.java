package com.tss.model;

import java.util.Scanner;

public class BitwiseOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		int number1 = 6, number2 = 3;
		System.out.println("\n--- Bitwise Operators ---");
		System.out.println("number1 is " + number1 + " & number2 is " + number2);
		System.out.println("Bitwise AND: " + (number1 & number2));
		System.out.println("Bitwise OR: " + (number1 | number2));
		System.out.println("Bitwise XOR: " + (number1 ^ number2));
		System.out.println("Left Shift (number1 << 1): " + (number1 << 1));
		System.out.println("Signed Right Shift (number1 >> 1): " + (number1 >> 1));
		System.out.println("Unsigned Right Shift (number1 >>> 1): " + (number1 >>> 1));
	}

}
