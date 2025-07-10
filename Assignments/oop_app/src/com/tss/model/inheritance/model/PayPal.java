package com.tss.model.inheritance.model;

import java.util.Scanner;

public class PayPal implements IPaymentMethod {
	private String email;

	public void acceptPaymentDetails() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter PayPal Email: ");
			email = scanner.nextLine();
			if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) break;
			System.out.println("Invalid email. e.g., name@example.com");
		}
	}

	@Override
	public void processPayment(double amount) {
		System.out.println("PayPal payment of " + amount + ", email: " + email);
	}
}
