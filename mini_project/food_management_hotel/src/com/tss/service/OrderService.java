package com.tss.service;

import java.util.List;
import java.util.Scanner;

import com.tss.model.FoodItem;
import com.tss.model.Order;
import com.tss.model.OrderItem;

public class OrderService {
    public void takeOrder(Order order, MenuService menuService, String selectedCuisine, Scanner scanner) {
        List<FoodItem> menuItems = menuService.getByCuisine(selectedCuisine);
        if (menuItems == null || menuItems.isEmpty()) {
            System.out.println("No items available in " + selectedCuisine + " cuisine.");
            return;
        }

        while (true) {
            System.out.print("Enter Food Item ID: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            int itemId = scanner.nextInt();
            scanner.nextLine();

            FoodItem selectedItem = menuItems.stream()
                .filter(item -> item.getId() == itemId)
                .findFirst()
                .orElse(null);

            if (selectedItem == null) {
                System.out.println("Invalid Food Item ID for " + selectedCuisine + " cuisine.");
                continue;
            }

            System.out.print("Enter Quantity: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            int quantity = scanner.nextInt();
            scanner.nextLine();
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                continue;
            }

            order.addItem(new OrderItem(selectedItem, quantity, 0));
            System.out.println(quantity + " x " + selectedItem.getName() + " added to order.");

            System.out.print("Do you want to add more items? (y/n): ");
            String moreItems = scanner.nextLine().trim().toLowerCase();
            if (!moreItems.equals("y") && !moreItems.equals("n")) {
                System.out.println("Please enter 'y' for yes or 'n' for no.");
                continue;
            }
            if (moreItems.equals("n")) {
                break;
            }
        }
    }
}