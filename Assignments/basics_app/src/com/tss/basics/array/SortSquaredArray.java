package com.tss.basics.array;

import java.util.Scanner;

public class SortSquaredArray {

	static int[] sortArr(int[] arr) {
		int n = arr.length;
		int result[] = new int[n];

		int left = 0;
		int right = n - 1;
		int pos = n - 1;

		while (left <= right) {
			int leftSqare = arr[left] * arr[left];
			int rightSqare = arr[right] * arr[right];

			if (leftSqare > rightSqare) {
				result[pos--] = leftSqare;
				left++;
			}

			else {
				result[pos--] = rightSqare;
				right--;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number : ");
		int elements = scanner.nextInt();
		
		int[] arr = new int[elements];
		
		System.out.println("Enter Elements : ");
		for(int i = 0; i < elements; i++) {
			arr[i] = scanner.nextInt();	
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i + 1] < arr[i]) {
				System.out.println("Sorry You have to enter sorted array");
				return;
			}
		}
		
		System.out.print("Original array : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		int[] newArr = sortArr(arr);
		
		System.out.println();
		System.out.print("Sorted squared : ");
		for (int i = 0; i < newArr.length; i++) {
			System.out.print(newArr[i] + " ");
		}
	}
}
