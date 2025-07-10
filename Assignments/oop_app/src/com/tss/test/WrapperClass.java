package com.tss.test;
import com.tss.model.Number;

public class WrapperClass {

	public static void main(String[] args) {
		Number number1 = new Number(10);
		Number number2 = new Number(20);

		System.out.println("Before Swap:");
		System.out.println("Number 1: " + number1);
		System.out.println("Number 2: " + number2);

		swap(number1, number2);

		System.out.println("After Swap:");
		System.out.println("Number 1: " + number1);
		System.out.println("Number 2: " + number2);
	}

	private static void swap(Number number1, Number number2) {
		Integer temp = number1.getNumber();
		number1.setNumber(number2.getNumber());
		number2.setNumber(temp);
	}

}
