package com.tss.model;

public class OstrichClass implements IBird, IWalkable {

	@Override
	public void walk() {
		System.out.println("Ostrich walks");
	}

	@Override
	public void getColor() {
		System.out.println("Color: Black and White");
	}

}
