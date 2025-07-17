package com.tss.service;

import java.util.Scanner;

import com.tss.exception.AppException;
import com.tss.model.Payment;

public class PaymentService {
	/**
	 * Processes a payment for a given amount.
	 * 
	 * @param amount  The amount to pay.
	 * @param scanner The scanner for user input.
	 * @return The created Payment object or null if processing fails.
	 * @throws AppException If payment processing fails.
	 */
	public Payment processPayment(double amount, Scanner scanner) throws AppException {
		Payment.Mode[] modes = Payment.Mode.values();
		System.out.println("Available Payment Modes:");
		for (int i = 0; i < modes.length; i++) {
			System.out.println((i + 1) + ". " + modes[i]);
		}
		int modeChoice = readIntInput(scanner, "Choose Payment Mode (1-" + modes.length + "): ", 1, modes.length);
		if (modeChoice == -1)
			return null;

		Payment.Mode mode = modes[modeChoice - 1];
		if (mode == Payment.Mode.UPI) {
			String pinInput = readPinInput(scanner);
			if (pinInput == null)
				return null;
		}
		return new Payment(mode, amount);
	}

	private int readIntInput(Scanner scanner, String prompt, int min, int max) {
		System.out.print(prompt);
		if (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
			scanner.nextLine();
			return -1;
		}
		int choice = scanner.nextInt();
		scanner.nextLine();
		if (choice < min || choice > max) {
			System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
			return -1;
		}
		return choice;
	}

	private String readPinInput(Scanner scanner) throws AppException {
		int maxAttempts = 3;
		for (int attempts = 0; attempts < maxAttempts; attempts++) {
			System.out.print("Enter 4-digit UPI PIN: ");
			String pinInput = scanner.nextLine().trim();
			if (pinInput.matches("\\d{4}")) {
				return pinInput;
			}
			System.out.println("Invalid PIN. Please enter exactly 4 digits.");
			if (attempts < maxAttempts - 1) {
				System.out.println("PIN attempts remaining: " + (maxAttempts - attempts - 1));
			}
		}
		throw new AppException("Too many invalid PIN attempts.");
	}
}