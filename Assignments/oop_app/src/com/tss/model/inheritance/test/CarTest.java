package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.ElectricCar;

public class CarTest{
	
	public static void main(String[] args) {
		ElectricCar test = new ElectricCar("BMW",2021,2500000,4,90.50);
		test.displayInfo();
		test.startEngine();
		test.stopEngine();
		test.lockDoors();
		test.unlockDoors();
		test.chargeBattery();
		test.displayBatteryStatus();
	}

}
