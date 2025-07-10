package com.tss.creational.vehicleapp.model;

public class HeavyVehicle implements IVehicle {

	@Override
	public String generatePlate() {
		return "HW" + (1000 + (int)(Math.random()*900000));
	}

}
