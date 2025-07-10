package com.tss.model.inheritance.test;

import java.util.Scanner;

import com.tss.model.inheritance.model.NewCircle;
import com.tss.model.inheritance.model.NewRectangle;

public class NewShapeTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter radius: ");
		double radius = scanner.nextDouble();
		NewCircle newCircle = new NewCircle(radius);
		newCircle.area();

		System.out.print("Enter length: ");
		double length = scanner.nextDouble();
		System.out.print("Enter width: ");
		double width = scanner.nextDouble();
		NewRectangle rectangle = new NewRectangle(length, width);
		rectangle.area();
	}

}
