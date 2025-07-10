package com.tss.model.inheritance.test;

import java.util.Scanner;
import com.tss.model.inheritance.model.Circle;
import com.tss.model.inheritance.model.Rectangle;
import com.tss.model.inheritance.model.Shape;

public class ShapeTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter radius: ");
		double radius = scanner.nextDouble();
		Shape circle = new Circle(radius);
		System.out.printf("Circle Area: %.2f\n", circle.getArea());

		System.out.print("Enter length: ");
		double length = scanner.nextDouble();
		System.out.print("Enter width: ");
		double width = scanner.nextDouble();
		Shape rectangle = new Rectangle(length, width);
		System.out.printf("Rectangle Area: %.2f\n", rectangle.getArea());

	}
}
