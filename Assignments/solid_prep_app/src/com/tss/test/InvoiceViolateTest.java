package com.tss.test;

import java.util.Scanner;

import com.tss.model.InvoiceViolateClass;

public class InvoiceViolateTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Invoice ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Cost: ");
        double cost = scanner.nextDouble();

        System.out.print("Enter Discount Percentage: ");
        double discountPercent = scanner.nextDouble();

        InvoiceViolateClass invoice = new InvoiceViolateClass(id, description, cost, discountPercent);
        invoice.printInvoice();
	}

}
