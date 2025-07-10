package com.tss.model;

public class LabourClass implements IWorkable, ILunchInterval{

	@Override
	public void eat() {
		System.out.println("Labour is Eating");
	}

	@Override
	public void rest() {
		System.out.println("Labour taking Rest");
	}

	@Override
	public void start() {
		System.out.println("Labour Starts Working");
	}

	@Override
	public void stop() {
		System.out.println("Labour Stops Working");
	}

}
