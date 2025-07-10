package com.tss.model;

public class RobotClass implements IWorkable{

	@Override
	public void start() {
		System.out.println("Robot Starts Working.");
	}

	@Override
	public void stop() {
		System.out.println("Robot Stops Working");
	}

}
