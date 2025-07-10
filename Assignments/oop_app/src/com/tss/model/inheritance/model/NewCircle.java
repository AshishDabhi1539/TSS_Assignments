package com.tss.model.inheritance.model;

public class NewCircle implements IShape{

	private double radius;
	
	public NewCircle(double radius) {
		this.radius = radius;
	}

	@Override
	public void area() {
		System.out.println("Area Of Circle : " + 3.14 * radius * radius);
	}

}
