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

		while (true) {
			try {
				int itemId = readIntInput(scanner, "Enter Food Item ID: ", 1, Integer.MAX_VALUE);
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

				order.addItem(new OrderItem(selectedItem, quantity, 0));
				System.out.println(quantity + " x " + selectedItem.getName() + " added to order.");

				String moreItems = readYesNoInput(scanner, "Do you want to add more items? (y/n): ");
				if (moreItems == null || moreItems.equals("n"))
					break;
			} catch (AppException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private int readIntInput(Scanner scanner, String prompt, int min, int max) {
		System.out.print(prompt);
		if (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
			scanner.nextLine();
			return -1;
		}
		int value = scanner.nextInt();
		scanner.nextLine();
		if (value < min || value > max) {
			System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
			return -1;
		}
		return value;
	}

	private String readYesNoInput(Scanner scanner, String prompt) {
		System.out.print(prompt);
		String input = scanner.nextLine().trim().toLowerCase();
		if (!input.equals("y") && !input.equals("n")) {
			System.out.println("Please enter 'y' for yes or 'n' for no.");
			return null;
		}
		return input;
	}
}