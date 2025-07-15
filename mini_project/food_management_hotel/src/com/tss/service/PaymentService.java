package com.tss.service;

import java.util.Scanner;
import com.tss.model.Payment;

public class PaymentService {
	public Payment processPayment(double amount, Scanner scanner) {
		System.out.print("Choose Payment Mode (1. Cash, 2. UPI): ");
		int modeChoice = scanner.nextInt();
		Payment.Mode mode = (modeChoice == 1) ? Payment.Mode.CASH : Payment.Mode.UPI;
		return new Payment(mode, amount);
	}
}
