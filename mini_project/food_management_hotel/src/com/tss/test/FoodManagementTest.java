package com.tss.test;

import java.util.Scanner;

import com.tss.model.Admin;
import com.tss.model.Customer;
import com.tss.model.DeliveryPartner;
import com.tss.model.FoodItem.Cuisine;
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
import com.tss.util.IDGenerator;
import com.tss.util.SerializationUtil;

public class FoodManagementTest {

	private static Admin admin;
	private static final String ADMIN_FILE = "admin.ser";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AdminService adminService = new AdminService();
		DiscountService discountService = new DiscountService(adminService.getDiscount());
		DeliveryService deliveryService = new DeliveryService(adminService.getPartners());
		InvoiceService invoiceService = new InvoiceService();

		// Load or create default admin
		admin = SerializationUtil.readObject(ADMIN_FILE);
		if (admin == null) {
			admin = new Admin("admin", "admin123");
			SerializationUtil.saveObject(admin, ADMIN_FILE);
		}

		try {
			while (true) {
				System.out.println("\n=== Mini Food Ordering System ===");
				System.out.println("1. Login as Admin");
				System.out.println("2. Order as Customer");
				System.out.println("0. Exit");
				System.out.print("Choose an option: ");
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

		if (admin.authenticate(user, pass)) {
			adminService.showAdminMenu(scanner);
		} else {
			System.out.println("Invalid admin credentials.");
		}
	}

	private static void handleCustomerOrder(Scanner scanner,
			AdminService adminService,
			DiscountService discountService,
			DeliveryService deliveryService,
			InvoiceService invoiceService) {

			CustomerService customerService = new CustomerService();
			Customer customer = customerService.handleCustomerAuth(scanner);
			Order order = new Order(IDGenerator.generateId(), customer);

			// Cuisine selection
			System.out.println("\nChoose Cuisine:");
			System.out.println("1. INDIAN");
			System.out.println("2. ITALIAN");
			System.out.print("Enter choice: ");
			int cuisineChoice = scanner.nextInt();
			scanner.nextLine();
			Cuisine selectedCuisine = (cuisineChoice == 1) ? Cuisine.INDIAN : Cuisine.ITALIAN;

			// Menu
			MenuService menuService = new MenuService(adminService.getMenu());
			System.out.println("\n--- " + selectedCuisine + " MENU ---");
			menuService.getByCuisine(selectedCuisine).forEach(System.out::println);

			// Take order
			OrderService orderService = new OrderService();
			orderService.takeOrder(order, menuService, scanner);

			double total = order.getTotal();
			double discount = discountService.getDiscountAmount(total);
			Payment payment = new PaymentService().processPayment(total - discount, scanner);
			DeliveryPartner partner = deliveryService.assignPartner();

			invoiceService.printInvoice(order, discount, payment, partner);
		}

}
