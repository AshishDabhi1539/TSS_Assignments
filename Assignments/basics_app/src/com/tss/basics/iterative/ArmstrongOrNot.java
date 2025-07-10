package com.tss.basics.iterative;

import java.util.Scanner;

public class ArmstrongOrNot {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Number : ");
		int digit = scanner.nextInt();
		
		int sum = 0;		
		int numOfDigit = String.valueOf(digit).length();
		int temp = digit;
		
		while(temp > 0) {
			int num = temp % 10;
			sum += Math.pow(num, numOfDigit);
			temp /= 10;
		}
		
		if(sum == digit) {
			System.out.println(digit + " is an Armstrong number.");
		}
		else {
			System.out.println(digit + " is not an Armstrong number.");
		}
	}

}
