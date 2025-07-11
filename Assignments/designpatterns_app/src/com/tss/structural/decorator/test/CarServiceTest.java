package com.tss.structural.decorator.test;

import java.util.Scanner;

import com.tss.structural.decorator.model.CarInspection;
import com.tss.structural.decorator.model.ICarService;
import com.tss.structural.decorator.model.OilChange;
import com.tss.structural.decorator.model.WheelAlign;

public class CarServiceTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        ICarService carService = new CarInspection(); // Base inspection always done first
        int choice;

        System.out.println("Base Car Inspection added. Cost: 1000");

        do {
            System.out.println("\n===== Car Service Menu =====");
            System.out.println("1. Add Oil Change (+500)");
            System.out.println("2. Add Wheel Alignment (+400)");
            System.out.println("3. Add Oil Change + Wheel Alignment (+900)");
            System.out.println("4. Display Total Cost");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    carService = new OilChange(carService);
                    System.out.println("Oil Change added to your service.");
                    break;
                case 2:
                    carService = new WheelAlign(carService);
                    System.out.println("Wheel Alignment added to your service.");
                    break;
                case 3:
                    carService = new WheelAlign(new OilChange(carService));
                    System.out.println("Oil Change and Wheel Alignment added to your service.");
                    break;
                case 4:
                    System.out.println("Total Service Cost: " + carService.getCost());
                    break;
                case 5:
                    System.out.println("Thank you! Visit again.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
	}

}
