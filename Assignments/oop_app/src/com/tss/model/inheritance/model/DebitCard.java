package com.tss.model.inheritance.model;

import java.util.Scanner;

public class DebitCard implements IPaymentMethod {
	private String cardNumber;
	private String cardHolder;
	private String pin;

	public void acceptPaymentDetails() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter 16-digit Debit Card Number: ");
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
			System.out.print("Enter 4-digit PIN: ");
			pin = scanner.nextLine();
			if (pin.matches("\\d{4}")) break;
			System.out.println("Invalid PIN.");
		}
	}

	@Override
	public void processPayment(double amount) {
		System.out.println("Debit Card payment of " + amount + ", card: " + cardNumber);
	}
}
