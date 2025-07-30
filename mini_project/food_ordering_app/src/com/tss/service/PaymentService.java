package com.tss.service;

import java.util.Scanner;

import com.tss.exception.AppException;
import com.tss.model.Customer;
import com.tss.model.Payment;
import com.tss.util.ValidationUtil;

public class PaymentService {
	public Payment processPayment(double amount, Scanner scanner, Customer customer) throws AppException {
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
			System.out.print("Enter UPI ID (e.g., user@bank): ");
			String upiIdInput = scanner.nextLine().trim();
			ValidationUtil.validateUpiId(upiIdInput);
			if (!upiIdInput.equals(customer.getUpiId())) {
				throw new AppException("UPI ID does not match your registered UPI ID.");
			}
			String pinInput = readPinInput(scanner, customer.getUpiPin());
			if (pinInput == null)
				return null;
		}
		System.out
				.println("Payment of ₹" + String.format("%.2f", amount) + " via " + mode + " processed successfully.");
		return new Payment(mode, amount);
	}

	private int readIntInput(Scanner scanner, String prompt, int min, int max) {
		System.out.print(prompt);
		try {
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println("Input cannot be empty. Please enter a number between " + min + " and " + max + ".");
				return -1;
			}
			int choice = Integer.parseInt(input);
			if (choice < min || choice > max) {
				System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
				return -1;
			}
			return choice;
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
			return -1;
		}
	}

	private String readPinInput(Scanner scanner, String correctPin) throws AppException {
		int maxAttempts = 3;
		for (int attempts = 0; attempts < maxAttempts; attempts++) {
			System.out.print("Enter 4-digit UPI PIN: ");
			String pinInput = scanner.nextLine().trim();
			ValidationUtil.validateUpiPin(pinInput);
			if (pinInput.equals(correctPin)) {
				return pinInput;
			}
			System.out.println("Incorrect PIN. " + (maxAttempts - attempts - 1) + " attempts remaining.");
		}
		throw new AppException("Too many incorrect PIN attempts. Payment failed.");
	}
}