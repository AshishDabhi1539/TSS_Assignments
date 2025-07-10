package com.tss.basics.commandline;

public class SimpleCalculator {
	
	public static void addition(int num1,int num2) {
		int Sum = num1 + num2;
		System.out.println("Sum = " + Sum);
	}
	
	public static void difference(int num1,int num2) {
		int Diff = num1 - num2;
		System.out.println("Difference = " + Diff);
	}

	public static void multiplication(int num1, int num2) {
		int Mul = num1 * num2;
		System.out.println("Multiplication = " + Mul);
	}
	
	public static void division(int num1, int num2) {
		int Div = num1 / num2;
		System.out.println("Division = " + Div);
	}
	
	public static void main(String[] args) {		
		int num1 = Integer.parseInt(args[0]);
		
		int num2 = Integer.parseInt(args[1]);
		
		addition(num1,num2);
		
		difference(num1,num2);
		
		multiplication(num1,num2);
		
		division(num1,num2);
		
	}

}
