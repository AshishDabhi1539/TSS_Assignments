package com.tss.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.tss.model.CustomerClass;
import com.tss.model.LineItemClass;
import com.tss.model.OrderClass;
import com.tss.model.ProductClass;

public class ShoppingCartApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<ProductClass> products = new ArrayList<>();

		System.out.println("How many products do you want to add in Store?");
		int productCount = scanner.nextInt();
		scanner.nextLine();

		for (int i = 1; i <= productCount; i++) {
			System.out.println("\nEnter details for product " + i + ":");
			System.out.print("Product Name : ");
			String name = scanner.nextLine();

			System.out.print("Product Price: ");
			double price = scanner.nextDouble();

			System.out.print("Product Discount %: ");
			double discount = scanner.nextDouble();
			scanner.nextLine();

			products.add(new ProductClass(i, name, price, discount));
		}

		System.out.println("\nWelcome to the Shopping Application!");

		System.out.print("Enter Customer ID: ");
		int customerId = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Customer Name: ");
		String customerName = scanner.nextLine();

		CustomerClass customer = new CustomerClass(customerId, customerName);

		System.out.print("Enter Order ID: ");
		int orderId = scanner.nextInt();
		scanner.nextLine();

		OrderClass order = new OrderClass(orderId, new Date());

		boolean addMore;
		do {
			System.out.println("\nAvailable Products:");
			for (ProductClass p : products) {
				System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Price: " + p.getPrice()
						+ ", Discount: " + p.getDiscountPercent() + "%");
			}

			System.out.print("Enter Product ID to add to order: ");
			int productId = scanner.nextInt();

			System.out.print("Enter Quantity: ");
			int quantity = scanner.nextInt();
			scanner.nextLine();

			ProductClass selectedProduct = products.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);

			if (selectedProduct != null) {
				LineItemClass item = new LineItemClass(order.getItems().size() + 1, quantity, selectedProduct);
				order.addItem(item);
			} else {
				System.out.println("Invalid Product ID!");
			}

			System.out.print("Do you want to add another item? (y/n): ");
			addMore = scanner.nextLine().equalsIgnoreCase("y");

		} while (addMore);

		customer.addOrder(order);

		printBill(customer, order);
	}

	private static void printBill(CustomerClass customer, OrderClass order) {
	    System.out.println("\n-- BILL --");
	    System.out.println("Customer ID:\t" + customer.getId());
	    System.out.println("Customer Name:\t" + customer.getName());
	    System.out.println("Order ID:\t" + order.getId());
	    System.out.println("Order Date:\t" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(order.getDate()));
	    System.out.println();

	    System.out.println("LineItem\tProduct ID\tItem\t\tQty\tUnit Price\tDiscount\tDisc. Price\tTotal Cost");
	
	    for (LineItemClass item : order.getItems()) {
	        ProductClass p = item.getProduct();
	        System.out.println(item.getId() + "\t\t" + p.getId() + "\t\t" + p.getName() + "\t\t" + item.getQuantity()
	                + "\t" + p.getPrice() + "\t\t" + p.getDiscountPercent() + "%" + "\t\t" + p.calculateDiscountPrice()
	                + "\t\t" + item.calculateLineItemCost());
	    }

	    System.out.println("Total Order Price:\t" + order.calculateOrderPrice());
	}


}
