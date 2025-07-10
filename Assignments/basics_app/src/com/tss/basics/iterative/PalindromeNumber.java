package com.tss.basics.iterative;

import java.util.Scanner;

public class PalindromeNumber {
	
	public static void isPalindrome(int number) {
		int actualNum = number;
		int reverseNumber = 0;
		
		while(number > 0) {
			int digit = number % 10;
			reverseNumber = reverseNumber * 10 + digit;
			number /= 10;
		}
		
		if(actualNum == number)
			System.out.println(actualNum + " is a palindrome: ");
		else
			System.out.println(actualNum + " is not a palindrome: ");
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Number : ");
		int number = scanner.nextInt();
		
		isPalindrome(number);
	}

}
