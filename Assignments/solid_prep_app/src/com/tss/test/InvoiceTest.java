package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Invoice;
import com.tss.model.InvoiceCalculator;
import com.tss.model.InvoicePrinter;

public class InvoiceTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        List<Invoice> invoices = new ArrayList<>();
        InvoiceCalculator calculator = new InvoiceCalculator();
        InvoicePrinter printer = new InvoicePrinter();

        System.out.print("Enter the number of invoices: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter detail of Invoice " + (i + 1));

            System.out.print("Enter Invoice ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Cost: ");
            double cost = scanner.nextDouble();

            System.out.print("Enter Discount Percentage: ");
            double discountPercent = scanner.nextDouble();
            scanner.nextLine();

            invoices.add(new Invoice(id, description, cost, discountPercent));
        }

        System.out.println();

        printer.printHeader();
        for (Invoice invoice : invoices) {
            printer.printInvoice(invoice, calculator);
        }
	}

}
