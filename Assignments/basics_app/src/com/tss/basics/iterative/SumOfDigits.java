package com.tss.basics.iterative;

import java.util.Scanner;

public class SumOfDigits {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		
		System.out.println("Enter Number : ");
		int digit = scanner.nextInt();
		
		while(digit > 0) {
			sum += digit % 10;			 
			digit = digit / 10;
		}
		
		System.out.println("Sum : " + sum);
	}

}
