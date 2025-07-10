package com.tss.model;

public class PigeonClass implements IBird, IFlyable, IWalkable {

	@Override
	public void walk() {
		System.out.println("Pigeon walks");
	}

	@Override
	public void fly() {
		System.out.println("Pigeon flies");
	}

	@Override
	public void getColor() {
		System.out.println("Color: Grey");
	}

}
