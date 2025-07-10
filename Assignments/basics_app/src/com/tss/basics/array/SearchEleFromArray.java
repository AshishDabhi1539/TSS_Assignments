package com.tss.basics.array;

import java.util.Scanner;

public class SearchEleFromArray {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Number : ");
		int elements = scanner.nextInt();

		searchNumber(elements,scanner);
	}

	private static void searchNumber(int elements,Scanner scanner) {
		int number[] = new int[elements];

		System.out.println("Enter Elements : ");
		for (int i = 0; i < elements; i++) {
			number[i] = scanner.nextInt();
		}

		System.out.print("Enter Elements which you want to search : ");
		int search = scanner.nextInt();
		
		for (int i = 0; i < number.length; i++) {
			if(number[i] == search)
				System.out.println("Find at index number : " + i);
		}
		
	}
}
