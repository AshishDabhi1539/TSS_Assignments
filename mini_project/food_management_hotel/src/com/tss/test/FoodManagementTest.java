package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.tss.model.Admin;
import com.tss.model.Customer;
import com.tss.model.DeliveryPartner;
import com.tss.model.Order;
import com.tss.model.Payment;
import com.tss.service.AdminService;
import com.tss.service.CustomerService;
import com.tss.service.DeliveryService;
import com.tss.service.DiscountService;
import com.tss.service.InvoiceService;
import com.tss.service.MenuService;
import com.tss.service.OrderService;
import com.tss.service.PaymentService;
import com.tss.util.ConfigUtil;
import com.tss.util.IDGenerator;

public class FoodManagementTest {

	private static Admin admin;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AdminService adminService = new AdminService();
		DiscountService discountService = new DiscountService(adminService.getDiscount());
		DeliveryService deliveryService = new DeliveryService(adminService.getPartners());
		InvoiceService invoiceService = new InvoiceService();

		Properties adminProps = ConfigUtil.loadProperties("admin.properties");
		String adminUser = adminProps.getProperty("username");
		String adminPass = adminProps.getProperty("password");

		admin = new Admin(adminUser, adminPass);

		try {
			while (true) {
				System.out.println("\n=== Mini Food Ordering System ===");
				System.out.println("1. Login as Admin");
				System.out.println("2. Order as Customer");
				System.out.println("0. Exit");
				System.out.print("Choose an option: ");
				if (!scanner.hasNextInt()) {
					System.out.println("Invalid input. Please enter a number (0, 1 or 2).");
					scanner.nextLine();
					continue;
				}
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1 -> handleAdminLogin(scanner, adminService);
				case 2 -> handleCustomerOrder(scanner, adminService, discountService, deliveryService, invoiceService);
				case 0 -> {
					System.out.println("Thank you for using our app!");
					return;
				}
				default -> System.out.println("Invalid choice.");
				}
			}
		} finally {
			scanner.close();
		}
	}

	private static void handleAdminLogin(Scanner scanner, AdminService adminService) {
		System.out.print("Enter username: ");
		String user = scanner.nextLine();
		System.out.print("Enter password: ");
		String pass = scanner.nextLine();

		if (admin != null && admin.authenticate(user, pass)) {
			adminService.showAdminMenu(scanner);
		} else {
			System.out.println("Invalid admin credentials.");
		}
	}

	private static void handleCustomerOrder(Scanner scanner, AdminService adminService, DiscountService discountService,
			DeliveryService deliveryService, InvoiceService invoiceService) {

		CustomerService customerService = new CustomerService();
		Customer customer = customerService.handleCustomerAuth(scanner);
		if (customer == null) {
			return; // Exit if authentication fails
		}

		Order order = new Order(IDGenerator.generateOrderId(), customer);
		List<String> cuisines = new ArrayList<>(adminService.getCustomCuisines());

		while (true) {
			System.out.println("\nChoose Cuisine:");
			for (int i = 0; i < cuisines.size(); i++) {
				System.out.println((i + 1) + ". " + cuisines.get(i));
			}
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
				continue;
			}
			int cuisineChoice = scanner.nextInt();
			scanner.nextLine();

			if (cuisineChoice == 0) {
				return; // Go back to main menu
			}

			if (cuisineChoice < 1 || cuisineChoice > cuisines.size()) {
				System.out.println("Invalid choice.");
				continue;
			}

			String selectedCuisine = cuisines.get(cuisineChoice - 1);
			MenuService menuService = new MenuService(adminService.getMenu());
			System.out.println("\n--- " + selectedCuisine + " MENU ---");
			menuService.getByCuisine(selectedCuisine).forEach(System.out::println);

			OrderService orderService = new OrderService();
			orderService.takeOrder(order, menuService, scanner);

			if (!order.getItems().isEmpty()) {
				double total = order.getTotal();
				double discount = discountService.getDiscountAmount(total);
				Payment payment = new PaymentService().processPayment(total - discount, scanner);
				DeliveryPartner partner = deliveryService.assignPartner();
				invoiceService.printInvoice(order, discount, payment, partner);
			}
			break; // Exit after placing order
		}
	}
}