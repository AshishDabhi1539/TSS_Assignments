package com.tss.basics;

import java.util.Scanner;

public class DistanceTwoPoints {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("X1 & X2 : ");
		int X1 = scanner.nextInt();
		int X2 = scanner.nextInt();
		
		System.out.print("Y1 & Y2 : ");
		int Y1 = scanner.nextInt();
		int Y2 = scanner.nextInt();
		
		double distance = Math.sqrt(Math.pow(X2 - X1, 2) + Math.pow(Y2 - Y1, 2));
		System.out.println("\nDistance : " + distance);

	}

}
