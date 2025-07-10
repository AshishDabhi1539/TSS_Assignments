package com.tss.basics.array;

import java.util.Scanner;

public class MaxOfElements {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Number : ");	
		int elements = scanner.nextInt();
		
		int number[] = new int[elements];
		
		System.out.println("Enter Elements : ");
		for(int i=0; i<elements; i++) {
			number[i]=scanner.nextInt();
		}		
		
		int max = 0;
		for(int i=0; i<number.length; i+=2) {
			if(number[i] > number[i+1]) {
				max = number[i];
			}
			else {
				max = number[i+1];
			}
		}
		System.out.print("Max of given numbers : " + max);
	}

}
