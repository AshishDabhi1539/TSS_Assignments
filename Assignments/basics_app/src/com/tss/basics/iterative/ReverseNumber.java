package com.tss.basics.iterative;

import java.util.Scanner;

public class ReverseNumber {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int reverseNumber = 0;
		
		System.out.print("Enter Number : ");
		int number = scanner.nextInt();
		
		System.out.println("Actual Number : " + number);
		
		while(number != 0) {
			int digit = number % 10;
			reverseNumber = reverseNumber * 10 + digit;
			number /= 10;
		}
		
		System.out.println("Reversed Number : " + reverseNumber);

	}

}
