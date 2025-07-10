package com.tss.basics.iterative;

import java.util.Scanner;

public class PerfectNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int sum = 0;
		
		System.out.print("Enter Number : ");
		int number = sc.nextInt();
		
		int i = 1;
		while (i <= number / 2) {
			if (number % i == 0) {
				sum += i;
			}
			i++;
		}

		if (sum == number) {
			System.out.println("\n" + number + " is Perfect Number");
		}
		else {
			System.out.println("\n" + number + " is not Perfect Number");
		}
	}

}
