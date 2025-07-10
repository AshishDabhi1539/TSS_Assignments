package com.tss.basics.array;

import java.util.Scanner;

public class SumOfAllEle {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Number : ");	
		int elements = scanner.nextInt();
		
		int number[] = new int[elements];
		
		System.out.println("Enter Elements : ");
		for(int i=0; i<elements; i++) {
			number[i]=scanner.nextInt();
		}		
		
		int sum = 0;
		for(int i=0; i<number.length; i++) {
			sum += number[i];
		}
		
		float avg = (float) sum / elements;
		System.out.println("Sum of All numbers : " + sum);
		System.out.println("Average of All numbers : " + avg);
	}

}
