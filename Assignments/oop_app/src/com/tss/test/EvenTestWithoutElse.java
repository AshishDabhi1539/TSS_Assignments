package com.tss.test;

import java.util.Scanner;

public class EvenTestWithoutElse {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Number: ");
		int number = scanner.nextInt();
		
		if((number%2) != 0)
		{
			System.out.println("number is not even");
			return;
		}
		System.out.println("number is even");
	}

}
