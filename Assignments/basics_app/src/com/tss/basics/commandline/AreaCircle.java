package com.tss.basics.commandline;

public class AreaCircle {
	
	public static void area(float radius) {
		float area = (float) (3.14 * radius * radius);
		System.out.println("Area = " + area);
	}

	public static void main(String[] args) {		
		System.out.print("Enter Radius : ");
		float radius = Float.parseFloat(args[0]);
		
		area(radius);

	}

}
