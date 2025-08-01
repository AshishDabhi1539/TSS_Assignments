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
import com.tss.util.ValidationUtil;

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
						System.out.println("Login successful! Welcome " + customer.getName() + ".");
						return customer;
					} else {
						throw new AppException("Invalid username or password.");
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
					ValidationUtil.validateUsername(name);
					System.out.print("Choose username: ");
					String user = scanner.nextLine().trim();
					ValidationUtil.validateUsername(user);
					String userUpper = user.toUpperCase();
					if (customerRepository.getAll().stream()
							.anyMatch(c -> c.getUsername().toUpperCase().equals(userUpper))) {
						throw new AppException("Username '" + user + "' is already taken. Please try another.");
					}
					System.out.print("Choose password: ");
					String pass = scanner.nextLine().trim();
					ValidationUtil.validatePassword(pass);
					System.out.print("Enter UPI ID (e.g., user@bank): ");
					String upiId = scanner.nextLine().trim();
					ValidationUtil.validateUpiId(upiId);
					System.out.print("Enter 4-digit UPI PIN: ");
					String upiPin = scanner.nextLine().trim();
					ValidationUtil.validateUpiPin(upiPin);
					Customer newCustomer = new Customer(IDGenerator.getInstance().generateCustomerId(), name, user,
							pass, upiId, upiPin);
					customerRepository.add(newCustomer);
					IDGenerator.getInstance().resetCustomerCounter(customerRepository.getAll());
					customerRepository.save();
					System.out.println("Registration successful! You can now log in, " + name + ".");
				} catch (AppException e) {
					System.out.println(e.getMessage());
				}
			}
			}
		} while (true);
	}

	public void manageCustomerOrder(Customer customer, AdminService adminService, Scanner scanner) {
		Order order = orderService.createOrder(customer);
		menuService.setMenu(adminService.getMenu());
		deliveryService.setPartners(adminService.getPartners());
		discountService.setDiscount(adminService.getDiscount());

		int choice;
		do {
			System.out.println("\n--- Customer Menu ---");
			System.out.println("1. View Menu by Cuisine");
			System.out.println("2. Add Item to Cart");
			System.out.println("3. Remove Item from Cart");
			System.out.println("4. Show Cart");
			System.out.println("5. Checkout");
			System.out.println("0. Exit");
			choice = readIntInput(scanner, "Enter choice: ", 0, 5);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1 -> viewMenuByCuisine(adminService.getCustomCuisines(), scanner);
			case 2 -> addItemToOrder(order, adminService.getCustomCuisines(), scanner);
			case 3 -> removeItemFromOrder(order, adminService.getCustomCuisines(), scanner);
			case 4 -> showCart(order);
			case 5 -> {
				order = checkout(order, scanner, customer);
				if (order == null) {
					order = orderService.createOrder(customer);
				}
			}
			case 0 -> {
				customerRepository.save();
				System.out.println("Data saved successfully.");
				System.out.println("Exiting Customer Menu...");
			}
			}
		} while (choice != 0);
	}

	private void showCart(Order order) {
		if (order.getItems().isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}
		System.out.println("\n--- Your Cart ---");
		printOrderTable(order.getItems());
		System.out.printf("Total: ₹%.2f%n", order.getTotal());
	}

	private Order checkout(Order order, Scanner scanner, Customer customer) {
		if (order.getItems().isEmpty()) {
			System.out.println("Your cart is empty. Please add items to proceed with checkout.");
			return order;
		}
		try {
			double total = order.getTotal();
			double discountThreshold = order.getCustomer().getDiscountThreshold();
			if (discountThreshold < 0) {
				throw new AppException("Invalid discount threshold. Please contact support.");
			}
			double discount = total > discountThreshold ? discountService.getDiscountAmount(total) : 0.0;
			double payable = total - discount;

			Payment payment = paymentService.processPayment(payable, scanner, customer);
			if (payment == null)
				return order;

			// Collect feedback after successful payment
			int starRating = readIntInput(scanner, "Rate your order (1-5 stars): ", 1, 5);
			if (starRating == -1)
				throw new AppException("Invalid rating. Feedback not saved.");
			System.out.print("Enter feedback note (max 500 characters, press Enter to skip): ");
			String feedbackNote = scanner.nextLine().trim();
			if (!feedbackNote.isEmpty() && feedbackNote.length() > 500) {
				throw new AppException("Feedback note cannot exceed 500 characters.");
			}
			orderService.addFeedback(order, starRating, feedbackNote);

			DeliveryPartner partner = deliveryService.assignPartner();
			if (partner.getId() == 0) {
				System.out.println("Warning: No delivery partner available. Order will be processed without delivery.");
			}
			invoiceService.printInvoice(order, discount, payment, partner);
			customerRepository.save();
			System.out.println("Checkout completed successfully. New cart created.");
			return orderService.createOrder(customer); // Create new order after checkout
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
		System.out.println("\n--- Add Item to Cart ---");
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
		System.out.println(quantity + " x " + item.getName() + " added to your cart.");
	}

	private void removeItemFromOrder(Order order, Set<String> cuisines, Scanner scanner) {
		if (order.getItems().isEmpty()) {
			System.out.println("Your cart is empty. No items to remove.");
			return;
		}
		System.out.println("\n--- Remove Item from Cart ---");
		printOrderTable(order.getItems());

		int maxCartId = order.getItems().stream().mapToInt(OrderItem::getCartId).max().orElse(0);
		int cartId = readIntInput(scanner, "Enter Cart ID to remove: ", 1, maxCartId);
		if (cartId == -1)
			return;

		OrderItem itemToRemove = order.getItems().stream().filter(item -> item.getCartId() == cartId).findFirst()
				.orElse(null);
		if (itemToRemove == null) {
			System.out.println("No item found with Cart ID " + cartId + " in your cart.");
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
					? itemToRemove.getItem().getName() + " (Cart ID: " + cartId + ") removed from your cart."
					: quantityToRemove + " x " + itemToRemove.getItem().getName() + " removed from your cart.");
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
		System.out.printf("+--------+----------------------+----------+----------+----------+%n");
		System.out.printf("| CartID | Name                 | Quantity | Subtotal | Cuisine  |%n");
		System.out.printf("+--------+----------------------+----------+----------+----------+%n");
		for (OrderItem item : items) {
			System.out.printf("| %-5d  | %-20s | %-8d | ₹%-7.2f | %-8s |%n", item.getCartId(), item.getItem().getName(),
					item.getQuantity(), item.getSubtotal(), item.getItem().getCuisine());
		}
		System.out.printf("+--------+----------------------+----------+----------+----------+%n");
	}
}