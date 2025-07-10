package com.tss.exception.test;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws RuntimeException {

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Enter 1st Number : ");
			int number1 = scanner.nextInt();

			System.out.print("Enter 2nd Number : ");
			int number2 = scanner.nextInt();
			float result = number1 / number2;

			System.out.println("Result : " + result);
		} catch (Exception exception) {
			System.out.println(exception);
		} finally {
			System.out.println("\nExiting...");
		}

		System.out.println("\nFinal Exiting...");

	}

}
