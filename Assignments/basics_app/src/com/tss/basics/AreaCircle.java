package com.tss.basics;

import java.util.Scanner;

public class AreaCircle {
	
	public static void circleArea(float radius) {
		double pi = 3.142;
		double circleArea = (pi * radius * radius);
		System.out.println("\nArea of Circle = " + circleArea);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Radius : ");
		float radius = sc.nextFloat();
		
		circleArea(radius);

	}

}
