package com.tss.mode;

public class Robot implements IWorker {

	@Override
	public void startWork() {
		System.out.println("Robot started working");
	}

	@Override
	public void stopWork() {
		System.out.println("Robot stopped working");
	}

	@Override
	public void eat() {
		System.out.println("Robot does not eat");
	}

	@Override
	public void drink() {
		System.out.println("Robot does not drink");
	}

}
