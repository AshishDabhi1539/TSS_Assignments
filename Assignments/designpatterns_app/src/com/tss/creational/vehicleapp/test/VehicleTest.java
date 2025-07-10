package com.tss.creational.vehicleapp.test;

import java.util.Scanner;

import com.tss.creational.vehicleapp.model.IVehicle;
import com.tss.creational.vehicleapp.model.VehicleFactory;
import com.tss.creational.vehicleapp.model.VehicleType;

public class VehicleTest {

	public static void main(String[] args) {

		VehicleFactory factory = new VehicleFactory();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Select Vehicle Type:");
		System.out.println("1. TwoWheeler");
		System.out.println("2. FourWheeler");
		System.out.println("3. HeavyVehicle");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();

		IVehicle vehicle = null;

		if (choice == 1) {
			vehicle = factory.makeVehicle(VehicleType.TwoWheeler);
		} else if (choice == 2) {
			vehicle = factory.makeVehicle(VehicleType.FourWheeler);
		} else if (choice == 3) {
			vehicle = factory.makeVehicle(VehicleType.HeavyVehicle);
		} else {
			System.out.println("Invalid choice! Please select between 1 and 3.");
		}

		if (vehicle != null) {
			System.out.println("Generated License Plate: " + vehicle.generatePlate());
		}

	}

}
