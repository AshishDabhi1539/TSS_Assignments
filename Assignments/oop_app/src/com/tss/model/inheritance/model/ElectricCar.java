package com.tss.model.inheritance.model;

public class ElectricCar extends Car {
	
	private double batteryLevel;

	public ElectricCar(String model, int year, int price, int numberOfDoors, double batteryLevel) {
		super(model, year, price, numberOfDoors);
		this.batteryLevel = batteryLevel;
	}

	public void chargeBattery() {
		System.out.println("Battery charging...");
	}
	
	public void displayBatteryStatus() {
		System.out.println("Battery Level : "+ batteryLevel);
	}

	public double getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(double batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

}
