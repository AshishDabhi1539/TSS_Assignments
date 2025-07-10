package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class MaxOFThree {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enetr 1st Number : ");
		int number1 = scanner.nextInt();
		
		System.out.print("Enetr 2nd Number : ");
		int number2 = scanner.nextInt();
		
		System.out.print("Enetr 3rd Number : ");
		int number3 = scanner.nextInt();
		
		if(number1 > number2) {
			if(number1 > number3){
				System.out.println("1st number is Max");
			}
			else {
				System.out.println("3rd number is Max");
			}
		}
		else {
			if(number2 > number3){
				System.out.println("2nd number is Max");
			}
			else {
				System.out.println("3rd number is Max");
			}
		}

	}

}