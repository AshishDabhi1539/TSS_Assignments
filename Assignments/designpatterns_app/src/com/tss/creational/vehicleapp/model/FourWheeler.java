package com.tss.creational.vehicleapp.model;

public class FourWheeler implements IVehicle {

	@Override
	public String generatePlate() {
		return "FW" + (1000 + (int)(Math.random()*9000));
	}

}
