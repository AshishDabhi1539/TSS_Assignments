package com.tss.creational.vehicleapp.model;

public class VehicleFactory {
	
	public IVehicle makeVehicle(VehicleType type) {

		if (type == VehicleType.TwoWheeler) {
			return new TwoWheeler();
		}

		if (type == VehicleType.FourWheeler) {
			return new FourWheeler();
		}

		if (type == VehicleType.HeavyVehicle) {
			return new HeavyVehicle();
		}

		return null;
	}
}
