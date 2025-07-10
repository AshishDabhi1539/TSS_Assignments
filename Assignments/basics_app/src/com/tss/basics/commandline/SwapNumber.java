package com.tss.basics.commandline;

public class SwapNumber {
	
	public static void swapWithThird(int number1, int number2) {
		System.out.println("Before Swap : number1:" + number1 + " number2:" + number2);
		int temp = number1;
		number1 = number2;
		number2 = temp;
		System.out.println("After Swap : number1:" + number1 + " number2:" + number2 + "\n");
	}
	
	public static void swapWithoutThird(int number1, int number2) {
		System.out.println("Before Swap : number1:" + number1 + " number2:" + number2);
		number1 = number1 + number2;
		number2 = number1 - number2;
		number1 = number1 - number2;
		System.out.println("After Swap : number1:" + number1 + " number2:" + number2);
	}

	public static void main(String[] args) {
		int number1 = Integer.parseInt(args[0]);
		
		int number2 = Integer.parseInt(args[1]);
		
		swapWithThird(number1, number2);
		
		swapWithoutThird(number1,number2);
		
	}

}
