package com.tss.model;

public class SparrowClass implements IBird, IFlyable, IWalkable {

	@Override
	public void walk() {
		System.out.println("Sparrow walks");
	}

	@Override
	public void fly() {
		System.out.println("Sparrow flies");
	}

	@Override
	public void getColor() {
		System.out.println("Color: Brown");
	}

}
