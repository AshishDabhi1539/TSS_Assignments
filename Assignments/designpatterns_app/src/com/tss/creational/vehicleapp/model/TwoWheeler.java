package com.tss.creational.vehicleapp.model;

public class TwoWheeler implements IVehicle {

	@Override
	public String generatePlate() {
		return "TW" + (1000 + (int)(Math.random()*9000));
	}

}
