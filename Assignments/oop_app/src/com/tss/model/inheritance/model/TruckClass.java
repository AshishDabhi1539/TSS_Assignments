package com.tss.model.inheritance.model;

public class TruckClass extends VehicleClass{
	
	public TruckClass(String vehicleNumber, double rentPerDay, String fuelType) {
        super(vehicleNumber, rentPerDay, fuelType);
    }

    @Override
    public double calculateRent(int numberOfDays) {
        return (numberOfDays * rentPerDay) + 500;
    }
}
