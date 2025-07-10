package com.tss.basics;

import java.util.Scanner;

public class SimpleCalculator {
	
	public static void addition(int number1,int number2) {
		int Sum = number1 + number2;
		System.out.println("Sum = " + Sum);
	}
	
	public static void difference(int number1,int number2) {
		int Difference = number1 - number2;
		System.out.println("Difference = " + Difference);
	}

	public static void multiplication(int number1, int number2) {
		int Multiplication = number1 * number2;
		System.out.println("Multiplication = " + Multiplication);
	}
	
	public static void division(int number1, int number2) {
		int Division = number1 / number2;
		System.out.println("Division = " + Division);
	}
	
	public static void main(String[] args) {		
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter 1st Num 1 : ");
		int number1 = scanner.nextInt();
		
		System.out.print("Enter 1st Num 2 : ");
		int number2 = scanner.nextInt();
		
		addition(number1,number2);
		
		difference(number1,number2);
		
		multiplication(number1,number2);
		
		division(number1,number2);
		
	}

}
