package com.tss.model.inheritance.model;

public class Car extends Vehicle {
	private int numberOfDoors;

	public Car(String model, int year, int price, int numberOfDoors) {
		super(model, year, price);
		this.numberOfDoors = numberOfDoors;
	}
	
	public void lockDoors() {
		System.out.println("Doors Loked...");
	}
	
	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	public void unlockDoors() {
		System.out.println("Doors Unlocked...");
	}
}
