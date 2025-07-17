package com.tss.controller;

import java.util.Properties;
import java.util.Scanner;

import com.tss.config.ConfigService;
import com.tss.exception.AppException;
import com.tss.model.Admin;
import com.tss.model.Customer;
import com.tss.service.AdminService;
import com.tss.service.CustomerService;
import com.tss.service.DeliveryService;
import com.tss.service.DiscountService;
import com.tss.service.InvoiceService;

public class FoodOrderController {
	private final AdminService adminService;
	private final CustomerService customerService;
	private final DiscountService discountService;
	private final DeliveryService deliveryService;
	private final InvoiceService invoiceService;
	private final Admin admin;

	public FoodOrderController() {
		this.adminService = new AdminService();
		this.discountService = new DiscountService(adminService.getDiscount());
		this.deliveryService = new DeliveryService(adminService.getPartners());
		this.invoiceService = new InvoiceService();
		this.customerService = new CustomerService();
		Properties adminProps = ConfigService.getInstance().loadProperties("resources/admin.properties");
		this.admin = new Admin(adminProps.getProperty("username"), adminProps.getProperty("password"));
	}

	/**
	 * Starts the food ordering system and handles the main menu.
	 */
	public void start() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				displayMainMenu();
				int choice = readIntInput(scanner, "Choose an option: ", 0, 2);
				if (choice == -1)
					continue;

				switch (choice) {
				case 1 -> handleAdminLogin(scanner);
				case 2 -> handleCustomerOrder(scanner);
				case 0 -> {
					System.out.println("Thank you for using our app!");
					return;
				}
				}
			}
		}
	}

	private void displayMainMenu() {
		System.out.println("\n=== Mini Food Ordering System ===");
		System.out.println("1. Login as Admin");
		System.out.println("2. Order as Customer");
		System.out.println("0. Exit");
	}

	private void handleAdminLogin(Scanner scanner) {
		System.out.print("Enter username: ");
		String user = scanner.nextLine();
		System.out.print("Enter password: ");
		String pass = scanner.nextLine();

		try {
			if (admin.authenticate(user, pass)) {
				adminService.showAdminMenu(scanner);
			} else {
				throw new AppException("Invalid admin credentials.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleCustomerOrder(Scanner scanner) {
		try {
			Customer customer = customerService.handleCustomerAuth(scanner);
			if (customer == null) {
				throw new AppException("Customer authentication failed. Please sign up or try again.");
			}
			customerService.manageCustomerOrder(customer, adminService, scanner);
			System.out.println("Order process completed. Returning to main menu.");
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
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

	public static void main(String[] args) {
		new FoodOrderController().start();
	}
}