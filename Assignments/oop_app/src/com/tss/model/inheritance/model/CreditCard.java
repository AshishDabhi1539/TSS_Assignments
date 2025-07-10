package com.tss.model.inheritance.model;

import java.util.Scanner;

public class CreditCard implements IPaymentMethod {
	private String cardNumber;
	private String cardHolder;
	private String expiryDate;

	public void acceptPaymentDetails() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter 16-digit Credit Card Number: ");
			cardNumber = scanner.nextLine();
			if (cardNumber.matches("\\d{16}")) break;
			System.out.println("Invalid card number.");
		}

		while (true) {
			System.out.print("Enter Card Holder Name: ");
			cardHolder = scanner.nextLine();
			if (cardHolder.matches("[a-zA-Z ]+")) break;
			System.out.println("Invalid name. Alphabets and space only.");
		}

		while (true) {
			System.out.print("Enter Expiry Date (MM/YY): ");
			expiryDate = scanner.nextLine();
			if (expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) break;
			System.out.println("Invalid expiry date.");
		}
	}

	@Override
	public void processPayment(double amount) {
		System.out.println("Credit Card payment of " + amount + ", card: " + cardNumber);
	}
}
