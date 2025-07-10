package com.tss.model.inheritance.model;

import java.util.Scanner;

public class UPI implements IPaymentMethod {
	private String upiId;

	public void acceptPaymentDetails() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter UPI ID (e.g., name@bank): ");
			upiId = scanner.nextLine();
			if (upiId.matches("^[\\w.-]+@[\\w]+$")) break;
			System.out.println("Invalid UPI ID. e.g., user123@upi");
		}
	}

	@Override
	public void processPayment(double amount) {
		System.out.println("UPI payment of " + amount + ", UPI ID: " + upiId);
	}
}
