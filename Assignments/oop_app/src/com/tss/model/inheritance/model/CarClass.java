package com.tss.model.inheritance.model;

public class CarClass extends VehicleClass {

	public CarClass(String vehicleNumber, double rentPerDay, String fuelType) {
		super(vehicleNumber, rentPerDay, fuelType);
	}

	@Override
	public double calculateRent(int numberOfDays) {
	    return numberOfDays * rentPerDay;
	}
	
}