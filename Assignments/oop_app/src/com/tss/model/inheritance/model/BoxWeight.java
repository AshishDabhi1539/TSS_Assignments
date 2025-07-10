package com.tss.model.inheritance.model;

public class BoxWeight extends Box{
	
	private int weight;
	private String message = "Boxweight";
	
	public BoxWeight(int weight,int width,int height,int depth)
	{
		super(width,height,depth);
		this.weight = weight;
	}
	
	public void displaybox()
	{
		System.out.println("Weight: "+this.weight);
		System.out.println(message);
		System.out.println(super.message);
	}
}