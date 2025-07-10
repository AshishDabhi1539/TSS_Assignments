package com.tss.model.inheritance.model;

public abstract class VehicleClass {
	protected String vehicleNumber;
    protected double rentPerDay;
    protected String fuelType;

	public VehicleClass(String vehicleNumber, double rentPerDay, String fuelType) {
        this.vehicleNumber = vehicleNumber;
        this.rentPerDay = rentPerDay;
        this.fuelType = fuelType;
    }

    public abstract double calculateRent(int numberOfDays);

    public void displayDetails(int numberOfDays) {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Rent Per Day: " + rentPerDay);
        System.out.println("Number of Days: " + numberOfDays);
        System.out.println("Total Rent: " + calculateRent(numberOfDays));
        System.out.println();
    }
}
