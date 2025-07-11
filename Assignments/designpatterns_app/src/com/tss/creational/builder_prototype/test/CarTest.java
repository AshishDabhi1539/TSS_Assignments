package com.tss.creational.builder_prototype.test;

import com.tss.creational.builder_prototype.model.Car;

public class CarTest {

	public static void main(String[] args) {
		Car car = new Car.CarBuilder()
                .setEngine("V8")
                .setWheels(4)
                .setColor("Red")
                .build();
		
		// Car car = new Car("V8", 4, "Red", 2023, false, "Leather", "Automatic", "Bluetooth", true, false);
		// Too many parameters → confusing us

		System.out.println(car);
	}

}
