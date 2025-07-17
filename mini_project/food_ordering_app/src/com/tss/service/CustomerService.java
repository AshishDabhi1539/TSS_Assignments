package com.tss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.tss.exception.AppException;
import com.tss.model.Customer;
import com.tss.model.DeliveryPartner;
import com.tss.model.FoodItem;
import com.tss.model.Order;
import com.tss.model.OrderItem;
import com.tss.model.Payment;
import com.tss.repository.CustomerRepository;
import com.tss.util.IDGenerator;

public class CustomerService {
	private final CustomerRepository customerRepository;
	private final MenuService menuService;
	private final OrderService orderService;
	private final DiscountService discountService;
	private final PaymentService paymentService;
	private final DeliveryService deliveryService;
	private final InvoiceService invoiceService;

	public CustomerService() {
		this.customerRepository = new CustomerRepository();
		this.menuService = new MenuService();
		this.orderService = new OrderService();
		this.discountService = new DiscountService();
		this.paymentService = new PaymentService();
		this.deliveryService = new DeliveryService(new ArrayList<>());
		this.invoiceService = new InvoiceService();
		IDGenerator.getInstance().loadInitialCounters(new ArrayList<>(), new ArrayList<>(),
				customerRepository.getAll());
	}

	/**
	 * Handles customer authentication (login or signup).
	 * 
	 * @param scanner The scanner for user input.
	 * @return The authenticated customer or null if authentication fails.
	 */
	public Customer handleCustomerAuth(Scanner scanner) {
		int option;
		do {
			System.out.println("\n1. Login");
			System.out.println("2. Sign Up");
			option = readIntInput(scanner, "Choose option: ", 1, 2);
			if (option == -1)
				continue;

			switch (option) {
			case 1 -> {
				try {
					System.out.print("Enter username: ");
					String user = scanner.nextLine().trim();
					if (user.isEmpty())
						throw new AppException("Username cannot be empty.");
					System.out.print("Enter password: ");
					String pass = scanner.nextLine().trim();
					if (pass.isEmpty())
						throw new AppException("Password cannot be empty.");
					Customer customer = customerRepository.findByCredentials(user, pass);
					if (customer != null) {
						System.out.println("Login successful! Welcome " + customer.getName());
						return customer;
					} else {
						throw new AppException("Invalid credentials.");
					}
				} catch (AppException e) {
					System.out.println(e.getMessage());
				}
			}
			case 2 -> {
				try {
					System.out.print("Enter your full name: ");
					String name = scanner.nextLine().trim();
					if (name.isEmpty())
						throw new AppException("Full name cannot be empty.");
					System.out.print("Choose username: ");
					String user = scanner.nextLine().trim();
					if (user.isEmpty())
						throw new AppException("Username cannot be empty.");
					String userUpper = user.toUpperCase();
					if (customerRepository.getAll().stream()
							.anyMatch(c -> c.getUsername().toUpperCase().equals(userUpper))) {
						throw new AppException("Username '" + user + "' already taken. Try another.");
					}
					System.out.print("Choose password: ");
					String pass = scanner.nextLine().trim();
					if (pass.isEmpty())
						throw new AppException("Password cannot be empty.");
					Customer newCustomer = new Customer(IDGenerator.getInstance().generateCustomerId(), name, user,
							pass);
					customerRepository.add(newCustomer);
					IDGenerator.getInstance().resetCustomerCounter(customerRepository.getAll());
					customerRepository.save();
					System.out.println("Registration successful. You can now login.");
				} catch (AppException e) {
					System.out.println(e.getMessage());
				}
			}
			}
		} while (true);
	}

	/**
	 * Manages the customer order process.
	 * 
	 * @param customer     The authenticated customer.
	 * @param adminService The admin service for accessing menu and partners.
	 * @param scanner      The scanner for user input.
	 */
	public void manageCustomerOrder(Customer customer, AdminService adminService, Scanner scanner) {
		Order order = orderService.createOrder(customer);
		menuService.setMenu(adminService.getMenu());
		deliveryService.setPartners(adminService.getPartners());
		discountService.setDiscount(adminService.getDiscount());

		int choice;
		do {
			System.out.println("\n--- Customer Menu ---");
			System.out.println("1. View Menu by Cuisine");
			System.out.println("2. Add Item to Buy");
			System.out.println("3. Remove Item from Buy");
			System.out.println("4. Print Invoice");
			System.out.println("0. Exit");
			choice = readIntInput(scanner, "Enter choice: ", 0, 4);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1 -> viewMenuByCuisine(adminService.getCustomCuisines(), scanner);
			case 2 -> addItemToOrder(order, adminService.getCustomCuisines(), scanner);
			case 3 -> removeItemFromOrder(order, adminService.getCustomCuisines(), scanner);
			case 4 -> {
				order = printInvoice(order, scanner, customer);
				if (order == null) {
					order = orderService.createOrder(customer); // Reset order after successful invoice
				}
			}
			case 0 -> {
				customerRepository.save();
				System.out.println("All data saved.");
				System.out.println("Exiting Customer Menu...");
			}
			}
		} while (choice != 0);
	}

	private Order printInvoice(Order order, Scanner scanner, Customer customer) {
		if (order.getItems().isEmpty()) {
			System.out.println("Cart is empty. Nothing to invoice.");
			return order;
		}
		try {
			double total = order.getTotal();
			double discountThreshold = order.getCustomer().getDiscountThreshold();
			if (discountThreshold < 0) {
				throw new AppException("Invalid discount threshold. Contact support.");
			}
			double discount = total > discountThreshold ? discountService.getDiscountAmount(total) : 0.0;
			double payable = total - discount;

			Payment payment = paymentService.processPayment(payable, scanner);
			if (payment == null)
				return order;

			DeliveryPartner partner = deliveryService.assignPartner();
			if (partner.getId() == 0) {
				System.out.println("Warning: No delivery partner available. Order will be processed without delivery.");
			}
			invoiceService.printInvoice(order, discount, payment, partner);
			customerRepository.save();
			return orderService.createOrder(customer); // Create new order after invoice
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return order;
		}
	}

	private void viewMenuByCuisine(Set<String> cuisines, Scanner scanner) {
		List<String> cuisineList = new ArrayList<>(cuisines);
		if (cuisineList.isEmpty()) {
			System.out.println("No cuisines available.");
			return;
		}
		System.out.println("\n--- View Menu by Cuisine ---");
		for (int i = 0; i < cuisineList.size(); i++) {
			System.out.println((i + 1) + ". " + cuisineList.get(i));
		}
		System.out.println("0. Back");
		int choice = readIntInput(scanner, "Enter choice: ", 0, cuisineList.size());
		if (choice == -1 || choice == 0)
			return;

		String cuisine = cuisineList.get(choice - 1);
		List<FoodItem> items = menuService.getByCuisine(cuisine);
		printMenuTable(items, cuisine);
	}

	private void addItemToOrder(Order order, Set<String> cuisines, Scanner scanner) {
		List<String> cuisineList = new ArrayList<>(cuisines);
		if (cuisineList.isEmpty()) {
			System.out.println("No cuisines available.");
			return;
		}
		System.out.println("\n--- Add Item to Buy ---");
		for (int i = 0; i < cuisineList.size(); i++) {
			System.out.println((i + 1) + ". " + cuisineList.get(i));
		}
		int cuisineChoice = readIntInput(scanner, "Select cuisine: ", 1, cuisineList.size());
		if (cuisineChoice == -1)
			return;

		String selectedCuisine = cuisineList.get(cuisineChoice - 1);
		List<FoodItem> items = menuService.getByCuisine(selectedCuisine);
		if (items.isEmpty()) {
			System.out.println("No items available in " + selectedCuisine + " cuisine.");
			return;
		}
		System.out.println("\nAvailable Food Items in " + selectedCuisine + ":");
		printMenuTable(items, selectedCuisine);

		int maxId = items.stream().mapToInt(FoodItem::getId).max().orElse(0);
		int itemId = readIntInput(scanner, "Enter Food Item ID: ", 1, maxId);
		if (itemId == -1)
			return;

		FoodItem item = items.stream().filter(i -> i.getId() == itemId).findFirst().orElse(null);
		if (item == null) {
			System.out.println("Invalid Food Item ID for " + selectedCuisine + " cuisine.");
			return;
		}
		int quantity = readIntInput(scanner, "Enter Quantity: ", 1, Integer.MAX_VALUE);
		if (quantity == -1)
			return;

		order.addItem(new OrderItem(item, quantity, 0), orderService);
		System.out.println(quantity + " x " + item.getName() + " added to cart.");
	}

	private void removeItemFromOrder(Order order, Set<String> cuisines, Scanner scanner) {
		if (order.getItems().isEmpty()) {
			System.out.println("Cart is empty. No items to remove.");
			return;
		}
		System.out.println("\n--- Remove Item from Buy ---");
		printOrderTable(order.getItems());

		int maxCartId = order.getItems().stream().mapToInt(OrderItem::getCartId).max().orElse(0);
		int cartId = readIntInput(scanner, "Enter Cart ID to remove: ", 1, maxCartId);
		if (cartId == -1)
			return;

		OrderItem itemToRemove = order.getItems().stream().filter(item -> item.getCartId() == cartId).findFirst()
				.orElse(null);
		if (itemToRemove == null) {
			System.out.println("No item found with Cart ID " + cartId + " in cart.");
			return;
		}

		int maxQuantity = itemToRemove.getQuantity();
		int quantityToRemove = maxQuantity > 1
				? readIntInput(scanner, "Enter quantity to remove (1 to " + maxQuantity + "): ", 1, maxQuantity)
				: 1;
		if (quantityToRemove == -1)
			return;

		boolean removed = order.removeItem(cartId, quantityToRemove);
		if (removed) {
			System.out.println(quantityToRemove == maxQuantity
					? "Item with Cart ID " + cartId + " (" + itemToRemove.getItem().getName() + ") removed from cart."
					: quantityToRemove + " x " + itemToRemove.getItem().getName() + " removed from cart.");
		}
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

	private void printMenuTable(List<FoodItem> items, String cuisine) {
		if (items.isEmpty()) {
			System.out.println("No items in " + cuisine + " cuisine.");
			return;
		}
		System.out.printf("+------+----------------------+----------+%n");
		System.out.printf("| ID   | Name                 | Price ₹  |%n");
		System.out.printf("+------+----------------------+----------+%n");
		for (FoodItem item : items) {
			System.out.printf("| %-4d | %-20s | %-8.2f |%n", item.getId(), item.getName(), item.getPrice());
		}
		System.out.printf("+------+----------------------+----------+%n");
	}

	private void printOrderTable(List<OrderItem> items) {
		System.out.printf("+-------+----------------------+----------+----------+----------+%n");
		System.out.printf("| CartID| Name                 | Quantity | Subtotal | Cuisine  |%n");
		System.out.printf("+-------+----------------------+----------+----------+----------+%n");
		for (OrderItem item : items) {
			System.out.printf("| %-5d | %-20s | %-8d | ₹%-7.2f | %-8s |%n", item.getCartId(), item.getItem().getName(),
					item.getQuantity(), item.getSubtotal(), item.getItem().getCuisine());
		}
		System.out.printf("+-------+----------------------+----------+----------+----------+%n");
	}
}