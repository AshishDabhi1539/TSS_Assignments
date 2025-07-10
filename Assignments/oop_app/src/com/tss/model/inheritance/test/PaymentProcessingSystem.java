package com.tss.model.inheritance.test;

import java.util.Scanner;

import com.tss.model.inheritance.model.IPaymentMethod;
import com.tss.model.inheritance.model.CreditCard;
import com.tss.model.inheritance.model.DebitCard;
import com.tss.model.inheritance.model.PayPal;
import com.tss.model.inheritance.model.UPI;

public class PaymentProcessingSystem {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IPaymentMethod paymentMethod = null;
		int choice = 0;

		do {
			while (true) {
				System.out.println("Select Payment Method:");
				System.out.println("1. Credit Card");
				System.out.println("2. Debit Card");
				System.out.println("3. UPI");
				System.out.println("4. PayPal");
				System.out.println("5. Exit");
				System.out.print("Enter choice (1-5): ");

				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
					scanner.nextLine();
					if (choice >= 1 && choice <= 5) break;
				} else {
					scanner.nextLine();
				}
				System.out.println("Invalid choice. Enter number from 1 to 5.");
			}

			switch (choice) {
				case 1 -> paymentMethod = new CreditCard();
				case 2 -> paymentMethod = new DebitCard();
				case 3 -> paymentMethod = new UPI();
				case 4 -> paymentMethod = new PayPal();
				case 5 -> System.exit(0);
			}

			paymentMethod.acceptPaymentDetails();

			double amount;
			while (true) {
				System.out.print("Enter payment amount: ");
				if (scanner.hasNextDouble()) {
					amount = scanner.nextDouble();
					if (amount > 0) break;
				}
				scanner.nextLine();
				System.out.println("Invalid amount. Enter valid amount.");
			}

			paymentMethod.processPayment(amount);
		}while(choice != 5);
	}
}
