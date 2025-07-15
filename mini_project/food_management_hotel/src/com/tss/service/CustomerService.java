package com.tss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Customer;
import com.tss.util.IDGenerator;
import com.tss.util.SerializationUtil;

public class CustomerService {
	private final String CUSTOMER_FILE = "customers.ser";
	private List<Customer> customers;

	public CustomerService() {
		customers = SerializationUtil.readList(CUSTOMER_FILE);
		if (customers == null)
			customers = new ArrayList<>();
	}

	public Customer handleCustomerAuth(Scanner scanner) {
		while (true) {
			System.out.println("\n1. Login");
			System.out.println("2. Sign Up");
			System.out.print("Choose option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1 -> {
				System.out.print("Enter username: ");
				String user = scanner.nextLine();
				System.out.print("Enter password: ");
				String pass = scanner.nextLine();

				// ✅ Validation for empty login fields
				if (user.trim().isEmpty() || pass.trim().isEmpty()) {
					System.out.println("Username or password cannot be empty.");
					break;
				}

				Customer customer = customers.stream().filter(c -> c.authenticate(user, pass)).findFirst().orElse(null);
				if (customer != null) {
					System.out.println("Login successful! Welcome " + customer.getName());
					return customer;
				} else {
					System.out.println("Invalid credentials.");
				}
			}
			case 2 -> {
				System.out.print("Enter your full name: ");
				String name = scanner.nextLine();
				System.out.print("Choose username: ");
				String user = scanner.nextLine();
				System.out.print("Choose password: ");
				String pass = scanner.nextLine();

				// ✅ Validation for empty signup fields
				if (user.trim().isEmpty() || pass.trim().isEmpty()) {
					System.out.println("Username or password cannot be empty.");
					break;
				}

				boolean exists = customers.stream().anyMatch(c -> c.getUsername().equals(user));
				if (exists) {
					System.out.println("Username already taken. Try again.");
				} else {
					Customer newCustomer = new Customer(IDGenerator.generateCustomerId(), name, user, pass);
					customers.add(newCustomer);
					saveCustomers();
					System.out.println("Registration successful. You can now login.");
				}
			}
			default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void saveCustomers() {
		SerializationUtil.saveList(customers, CUSTOMER_FILE);
	}
}
