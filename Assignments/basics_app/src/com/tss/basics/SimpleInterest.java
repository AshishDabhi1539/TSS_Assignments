package com.tss.basics;

import java.util.Scanner;

public class SimpleInterest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Principle Amount : ");
		float principle = sc.nextInt();
		
		System.out.print("Enter Rate of interest : ");
		float rate = sc.nextFloat();
		
		System.out.print("Enter Duration : ");
		float time = sc.nextInt();
		
		float simpleInterest = (principle * rate * time) / 100;
		
		System.out.print("\nSimple Interest : " + simpleInterest);

	}

}
