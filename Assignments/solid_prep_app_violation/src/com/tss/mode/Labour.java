package com.tss.mode;

public class Labour implements IWorker {

	@Override
	public void startWork() {
		System.out.println("Labour started working");
	}

	@Override
	public void stopWork() {
		System.out.println("Labour stopped working");
	}

	@Override
	public void eat() {
		System.out.println("Labour is eating");
	}

	@Override
	public void drink() {
		System.out.println("Labour is drinking");
	}

}
