package com.tss.model;

public class ElectricityBill {
	private int unitsConsumed,apartmentNumber;
	private static double costPerUnit;
	
	public ElectricityBill(int unitsConsumed, int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
		this.unitsConsumed = unitsConsumed;
	}

	public int getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public int getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public static double getCostPerUnit() {
		return costPerUnit;
	}

	public static void setCostPerUnit(double costPerUnit) {
		ElectricityBill.costPerUnit = costPerUnit;
	}

	public double calculateBillAmount() {
        return unitsConsumed * costPerUnit;
    }

    public static void displayCurrentRate() {
        System.out.println("Current cost per unit: " + costPerUnit);
    }

    public void displayBill() {
        System.out.println("Apartment Number: " + apartmentNumber);
        System.out.println("Units Consumed: " + unitsConsumed);
        System.out.println("Cost per Unit: " + costPerUnit);
        System.out.println("Total Bill Amount: " + calculateBillAmount());
    }
}
