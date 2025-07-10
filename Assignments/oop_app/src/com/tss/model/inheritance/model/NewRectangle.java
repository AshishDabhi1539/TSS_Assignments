package com.tss.model.inheritance.model;

public class NewRectangle implements IShape {

	private double length;
    private double width;
	
	public NewRectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	@Override
	public void area() {
		System.out.println("Area Of Rectangle : " + length * width);
	}

}
