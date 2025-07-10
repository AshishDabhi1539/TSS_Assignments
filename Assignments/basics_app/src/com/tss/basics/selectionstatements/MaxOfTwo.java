package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class MaxOfTwo {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enetr 1st Number : ");
		int number1 = scanner.nextInt();
		
		System.out.print("Enetr 2nd Number : ");
		int number2 = scanner.nextInt();
		
		if(number1 > number2) {
			System.out.println("1st Number is Max");
		}
		else {
			System.out.println("2nd number is Max");
		}

	}

}
