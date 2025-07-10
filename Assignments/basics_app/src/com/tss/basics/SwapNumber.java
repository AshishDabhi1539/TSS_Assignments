package com.tss.basics;

import java.util.Scanner;

public class SwapNumber {
	
	public static void swapWithThird(int number1, int number2) {
		System.out.println("\nBefore Swap : number1: " + number1 + ", number2: " + number2);
		int temp = number1;
		number1 = number2;
		number2 = temp;
		System.out.println("After Swap : number1: " + number1 + ", number2: " + number2 + "\n");
	}
	
	public static void swapWithoutThird(int number1, int number2) {
		System.out.println("Before Swap : number1: " + number1 + ", number2: " + number2);
		number1 = number1 + number2;
		number2 = number1 - number2;
		number1 = number1 - number2;
		System.out.println("After Swap : number1: " + number1 + ", number2: " + number2);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter 1st number : ");
		int number1 = scanner.nextInt();
		
		System.out.print("Enter 1st number : ");
		int number2 = scanner.nextInt();
		
		swapWithThird(number1, number2);
		
		swapWithoutThird(number1,number2);
		
	}

}
