package com.tss.service;

import java.util.Scanner;

import com.tss.model.FoodItem;
import com.tss.model.Order;
import com.tss.model.OrderItem;

public class OrderService {
	public void takeOrder(Order order, MenuService menuService, Scanner scanner) {
		char choice = 'y';

		do {
			System.out.print("Enter Food Item ID: ");
			int id = scanner.nextInt();
			FoodItem item = menuService.getItemById(id);
			if (item == null) {
				System.out.println("Invalid item ID.");
				continue;
			}
			System.out.print("Enter Quantity: ");
			int qty = scanner.nextInt();
			if (qty <= 0) {
				System.out.println("Quantity must be greater than 0.");
				continue;
			}
			order.addItem(new OrderItem(item, qty));
			System.out.print("Do you want to add more items? (y/n): ");
			choice = scanner.next().charAt(0);
		} while (choice == 'y' || choice == 'Y');

		if (order.getItems().isEmpty()) {
			System.out.println("Order is empty. No items selected.");
		}
	}

}
