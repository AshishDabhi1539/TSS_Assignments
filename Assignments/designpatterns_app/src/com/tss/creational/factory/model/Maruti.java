package com.tss.creational.factory.model;

public class Maruti implements ICars {

	@Override
	public void start() {
		System.out.println("Maruti Starts");
	}

	@Override
	public void stop() {
		System.out.println("Maruti Stops");
	}

}
