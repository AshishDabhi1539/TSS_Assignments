package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class EvenOddNumber {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enetr Number : ");
		int number = scanner.nextInt();
		
		if(number % 2 == 0) {
			System.out.println(number + " is Even");
		}
		else {
			System.out.println(number + " is not Even");
		}

	}

}
