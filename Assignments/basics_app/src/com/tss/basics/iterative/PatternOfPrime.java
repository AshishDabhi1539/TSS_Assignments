package com.tss.basics.iterative;

import java.util.Scanner;

public class PatternOfPrime {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the number of Row : ");
		int row = scanner.nextInt();
		
		int iteration = (row * (row + 1))/2;
		int primeList[] = new int[iteration];
		
		int index = 0;
		int num = 2;
		while(index < iteration) {
			if(isPrime(num)) {
				primeList[index] = num;
				index++;
			}
			num++;
		}
		
		System.out.println("Prime numbers pattern.");
		index = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print(primeList[index] + " ");
				index++;
			}
			System.out.println();
		}

	}
	
	public static boolean isPrime(int num) {
		if(num <= 1) return false;
		int i = 2;
		while(i <= Math.sqrt(num)) {
			if(num % i == 0)
				return false;
			i++;
		}
		return true;
	}

}
