package com.tss.model.inheritance.model;

public class Box {
	private int width;
	private int height;
	private int depth;
	
	public String message = "BoxClass";
	
	public Box(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public void initialize(int width,int height,int depth)
	{
		this.depth=depth;
		this.height=height;
		this.width=width;
	}
	
	public void displayBox()
	{
		System.out.println("Height: "+height);
		System.out.println("Width: "+width);
		System.out.println("Depth: "+depth);
	}
}
