package com.tss.model.inheritance.model;

public class BikeClass extends VehicleClass {
	
	public BikeClass(String vehicleNumber, double rentPerDay, String fuelType) {
		super(vehicleNumber, rentPerDay, fuelType);
	}

	@Override
    public double calculateRent(int numberOfDays) {
        double total = numberOfDays * rentPerDay;
        if (numberOfDays > 5) {
            total = total * 0.9;
        }
        return total;
    }
}
