package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class RideGameCalculator {

	public static void main(String[] args) {

		int bill = 0;
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Your Height : ");
		float height = scanner.nextFloat();

		if (height > 120) {
			System.out.println("You can Ride...");

			System.out.print("Enter Age : ");
			int age = scanner.nextInt();

			if (age < 12) {
				bill += 5;
			}

			else if ((age >= 12) && (age < 18)) {
				bill += 7;
			} else if ((age >= 18) && (age < 45)) {
				bill += 12;
			} else if ((age >= 45) && (age <= 55)) {
				bill += 0;
			} else if (age > 55) {
				bill += 12;
			}

			System.out.println("Want Photo?");
			System.out.print("Enetr True if Yes or False if No : ");
			Boolean photo = scanner.nextBoolean();

			if (photo) {
				bill += 3;
				System.out.println("Bill : " + bill);
			} else {
				System.out.println("Bill : " + bill);
			}

		} else {
			System.out.println("Can't ride...");
		}

	}

}
