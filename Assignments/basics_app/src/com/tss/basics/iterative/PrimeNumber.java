package com.tss.basics.iterative;

import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Number : ");
		int number = scanner.nextInt();
		
		int i = 2,flag = 0;
		while(i < number) {
			if(number % i == 0) {
				flag = 0;
				break;
			}
			else {
				flag = 1;
			}
			i++;
		}
		
		if(flag == 1) {
			System.out.println(number + " is a prime number.");
		}
		else {
			System.out.println(number + " is not a prime number.");
		}
	}
}
