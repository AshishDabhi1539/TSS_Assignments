package com.tss.basics.array;

import java.util.Scanner;

public class AvgArrayEle {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Number : ");
        int elements = scanner.nextInt();

        int number[] = new int[elements];

        System.out.println("Enter Elements : ");
        for (int i = 0; i < elements; i++) {
            number[i] = scanner.nextInt();
        }
        
        boolean result = compareElements(number);
        System.out.println("Result : " + result);
	}

	private static boolean compareElements(int[] number) {
		int sum = 0;
		
		for(int i = 0; i < number.length; i++) {
			sum = sum + number[i];
		}
		
		//if (sum % number.length != 0) return false;
		int average = sum / number.length;
		
		for(int i = 0; i < number.length; i++) {
			for(int j = i + 1; j < number.length; j++) {
				if((number[i] + number[j]) == average) 
					return true;
			}
		}
		return false;
	}

}
