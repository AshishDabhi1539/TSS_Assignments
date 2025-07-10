package com.tss.creational.factory.model;

public class Tata implements ICars {

	@Override
	public void start() {
		System.out.println("Tata Starts");
	}

	@Override
	public void stop() {
		System.out.println("Tata Stops");
	}

}
