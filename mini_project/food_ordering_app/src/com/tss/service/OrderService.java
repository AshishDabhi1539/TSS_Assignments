package com.tss.service;

import java.util.List;
import java.util.Scanner;

import com.tss.exception.AppException;
import com.tss.model.Customer;
import com.tss.model.FoodItem;
import com.tss.model.Order;
import com.tss.model.OrderItem;
import com.tss.util.IDGenerator;

public class OrderService {
	/**
	 * Creates a new order for a customer.
	 * 
	 * @param customer The customer placing the order.
	 * @return The created order.
	 */
	public Order createOrder(Customer customer) {
		return new Order(IDGenerator.getInstance().generateOrderId(), customer);
	}

	/**
	 * Generates a unique cart ID for an order item.
	 * 
	 * @return The generated cart ID.
	 */
	public int generateCartId() {
		return IDGenerator.getInstance().generateCartId();
	}

	/**
	 * Takes an order by adding items to it.
	 * 
	 * @param order           The order to add items to.
	 * @param menuService     The menu service for accessing food items.
	 * @param selectedCuisine The selected cuisine.
	 * @param scanner         The scanner for user input.
	 */
	public void takeOrder(Order order, MenuService menuService, String selectedCuisine, Scanner scanner) {
		List<FoodItem> menuItems = menuService.getByCuisine(selectedCuisine);
		if (menuItems.isEmpty()) {
			System.out.println("No items available in " + selectedCuisine + " cuisine.");
			return;
		}
		System.out.println("\nAvailable Food Items in " + selectedCuisine + ":");
		printMenuTable(menuItems, selectedCuisine);

		while (true) {
			try {
				int maxId = menuItems.stream().mapToInt(FoodItem::getId).max().orElse(0);
				int itemId = readIntInput(scanner, "Enter Food Item ID: ", 1, maxId);
				if (itemId == -1)
					continue;

				FoodItem selectedItem = menuItems.stream().filter(item -> item.getId() == itemId).findFirst()
						.orElse(null);
				if (selectedItem == null) {
					throw new AppException("Invalid Food Item ID for " + selectedCuisine + " cuisine.");
				}

				int quantity = readIntInput(scanner, "Enter Quantity: ", 1, Integer.MAX_VALUE);
				if (quantity == -1)
					continue;

				order.addItem(new OrderItem(selectedItem, quantity, 0), this);
				System.out.println(quantity + " x " + selectedItem.getName() + " added to order.");

				String moreItems = readYesNoInput(scanner, "Do you want to add more items? (y/n): ");
				if (moreItems == null || moreItems.equalsIgnoreCase("n"))
					break;
			} catch (AppException e) {
				System.out.println(e.getMessage());
			}
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

	private String readYesNoInput(Scanner scanner, String prompt) {
		System.out.print(prompt);
		String input = scanner.nextLine().trim().toLowerCase();
		if (input.isEmpty() || (!input.equals("y") && !input.equals("n"))) {
			System.out.println("Invalid input. Please enter 'y' or 'n'.");
			return null;
		}
		return input;
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
}