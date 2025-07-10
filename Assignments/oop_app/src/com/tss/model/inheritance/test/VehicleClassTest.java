package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.BikeClass;
import com.tss.model.inheritance.model.CarClass;
import com.tss.model.inheritance.model.TruckClass;
import com.tss.model.inheritance.model.VehicleClass;

public class VehicleClassTest {

	public static void main(String[] args) {
		
		VehicleClass car = new CarClass("CAR123", 1000, "Petrol");
        VehicleClass bike = new BikeClass("BIKE456", 500, "Petrol");
        VehicleClass truck = new TruckClass("TRUCK789", 1500, "Diesel");

        int carDays = 3;
        int bikeDays = 6;
        int truckDays = 2;

        car.displayDetails(carDays);
        bike.displayDetails(bikeDays);
        truck.displayDetails(truckDays);
	}

}
