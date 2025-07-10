package com.tss.basics.array;

import java.util.Scanner;

public class BasicArray {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int number[] = new int[5];
		
		System.out.print("Enter 5 Elements : ");
		for(int i=0; i<5; i++) {
			number[i]=scanner.nextInt();
		}		
		
		System.out.print("Elements are : ");
		for(int i=0; i<number.length; i++) {
			System.out.print(number[i] + "\t");
		}
	}

}
