package com.tss.service;

import java.util.Scanner;
import com.tss.model.Payment;

public class PaymentService {
    public Payment processPayment(double amount, Scanner scanner) {
        System.out.print("Choose Payment Mode (1. Cash, 2. UPI): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            scanner.nextLine();
            return null; // Or throw an exception
        }
        int modeChoice = scanner.nextInt();
        scanner.nextLine();
        Payment.Mode mode = (modeChoice == 1) ? Payment.Mode.CASH : Payment.Mode.UPI;
        return new Payment(mode, amount);
    }
}