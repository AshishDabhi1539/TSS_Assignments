package com.tss.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.tss.model.DeliveryPartner;
import com.tss.model.Discount;
import com.tss.model.FoodItem;
import com.tss.util.IDGenerator;
import com.tss.util.SerializationUtil;

public class AdminService {
	private List<FoodItem> menu;
	private List<DeliveryPartner> partners;
	private Discount discount;
	private Set<String> customCuisines = new HashSet<>(Arrays.asList("INDIAN", "ITALIAN"));

	private final String MENU_FILE = "menu.ser";
	private final String PARTNER_FILE = "partners.ser";
	private final String DISCOUNT_FILE = "discount.ser";

	public AdminService() {
		this.menu = SerializationUtil.readList(MENU_FILE);
		this.partners = SerializationUtil.readList(PARTNER_FILE);
		this.discount = SerializationUtil.readObject(DISCOUNT_FILE);

		if (menu == null)
			menu = new ArrayList<>();
		if (partners == null)
			partners = new ArrayList<>();
		if (discount == null)
			discount = new Discount(500, 50);
	}

	public void showAdminMenu(Scanner scanner) {
		int choice = -1;
		do {
			System.out.println("\n--- Admin Panel ---");
			System.out.println("1. Manage Menu");
			System.out.println("2. Manage Delivery Partners");
			System.out.println("3. Manage Discount");
			System.out.println("0. Exit Admin Panel");
			System.out.print("Enter choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number (0, 1, 2 or 3).");
				scanner.nextLine();
				continue;
			}
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> manageMenu(scanner);
			case 2 -> managePartners(scanner);
			case 3 -> manageDiscount(scanner);
			case 0 -> {
				saveAll();
				System.out.println("Exiting Admin Panel...");
			}
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 0);
	}

	private void manageMenu(Scanner scanner) {
		int choice = -1;
		do {
			System.out.println("\n--- Menu Management ---");
			System.out.println("1. View Menu by Cuisine");
			System.out.println("2. Add Food Item");
			System.out.println("3. Update Food Item");
			System.out.println("4. Remove Food Item");
			System.out.println("5. Manage Cuisine");
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number (0, 1, 2, 3, 4 or 5).");
				scanner.nextLine();
				continue;
			}
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> viewMenuByCuisine(scanner);
			case 2 -> {
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter price: ");
				while (!scanner.hasNextDouble()) {
					System.out.println("Invalid input. Please enter a numeric value for price.");
					scanner.nextLine();
					System.out.print("Enter price: ");
				}
				double price = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Available Cuisines: " + customCuisines);
				System.out.print("Enter cuisine name: ");
				String cuisineInput = scanner.nextLine().toUpperCase();
				if (!customCuisines.contains(cuisineInput)) {
					System.out.println("Invalid cuisine. Please add it first in 'Manage Cuisine'.");
					break;
				}
				menu.add(new FoodItem(IDGenerator.generateFoodItemId(), name, price, cuisineInput));
				System.out.println("Food item added.");
			}
			case 3 -> {
				if (menu.isEmpty()) {
					System.out.println("No items available to update.");
					break;
				}
				System.out.print("Enter Food ID to update: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				FoodItem item = menu.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
				if (item != null) {
					System.out.print("New name: ");
					item.setName(scanner.nextLine());
					System.out.print("New price: ");
					while (!scanner.hasNextDouble()) {
						System.out.println("Invalid input. Please enter a numeric value for price.");
						scanner.nextLine();
						System.out.print("New price: ");
					}
					item.setPrice(scanner.nextDouble());
					scanner.nextLine();
					System.out.print("New cuisine name: ");
					String newCuisine = scanner.nextLine().toUpperCase();
					if (customCuisines.contains(newCuisine)) {
						item.setCuisine(newCuisine);
						System.out.println("Item updated.");
					} else {
						System.out.println("Invalid cuisine name.");
					}
				} else {
					System.out.println("Food item not found.");
				}
			}
			case 4 -> {
				if (menu.isEmpty()) {
					System.out.println("No items available to remove.");
					break;
				}
				System.out.print("Enter Food ID to remove: ");
				int id = scanner.nextInt();
				boolean removed = menu.removeIf(f -> f.getId() == id);
				System.out.println(removed ? "Item removed." : "No item found with ID " + id);
			}
			case 5 -> manageCuisine(scanner);
			case 0 -> {
				saveAll();
				return;
			}
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 0);
	}

	private void viewMenuByCuisine(Scanner scanner) {
		System.out.println("\n--- View Menu by Cuisine ---");
		System.out.println("Available Cuisines: " + customCuisines);
		System.out.print("Enter cuisine name: ");
		String cuisineName = scanner.nextLine().toUpperCase();
		if (!customCuisines.contains(cuisineName)) {
			System.out.println("Cuisine not found.");
			return;
		}
		List<FoodItem> filtered = menu.stream().filter(f -> f.getCuisine().equalsIgnoreCase(cuisineName))
				.collect(Collectors.toList());
		if (filtered.isEmpty()) {
			System.out.println("No items in " + cuisineName + " cuisine.");
		} else {
			System.out.printf("+------+----------------------+----------+%n");
			System.out.printf("| ID   | Name                 | Price ₹  |%n");
			System.out.printf("+------+----------------------+----------+%n");
			for (FoodItem item : filtered) {
				System.out.printf("| %-4d | %-20s | %-8.2f |%n", item.getId(), item.getName(), item.getPrice());
			}
			System.out.printf("+------+----------------------+----------+%n");
		}
	}

	private void manageCuisine(Scanner scanner) {
		int choice = -1;
		do {
			System.out.println("\n--- Manage Cuisine ---");
			System.out.println("1. View All Cuisines");
			System.out.println("2. Add New Cuisine");
			System.out.println("3. Remove Cuisine");
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number (0, 1, 2 or 3).");
				scanner.nextLine();
				continue;
			}
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> {
				System.out.println("Available Cuisines:");
				customCuisines.forEach(c -> System.out.println("- " + c));
			}
			case 2 -> {
				System.out.print("Enter new cuisine name: ");
				String newCuisine = scanner.nextLine().toUpperCase();
				if (customCuisines.contains(newCuisine)) {
					System.out.println("Cuisine already exists.");
				} else {
					customCuisines.add(newCuisine);
					System.out.println("Cuisine added: " + newCuisine);
				}
			}
			case 3 -> {
				System.out.print("Enter cuisine name to remove: ");
				String toRemove = scanner.nextLine().toUpperCase();
				if (!customCuisines.contains(toRemove)) {
					System.out.println("Cuisine not found.");
				} else {
					customCuisines.remove(toRemove);
					System.out.println("Cuisine removed: " + toRemove);
				}
			}
			case 0 -> {
				saveAll();
				System.out.println("Returning to Menu Management...");
			}
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 0);
	}

	private void managePartners(Scanner scanner) {
		while (true) {
			System.out.println("\n--- Delivery Partner Management ---");
			System.out.println("1. View Partners");
			System.out.println("2. Add Partner");
			System.out.println("3. Update Partner");
			System.out.println("4. Remove Partner");
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number (0, 1, 2, 3 or 4).");
				scanner.nextLine();
				continue;
			}
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> {
				if (partners.isEmpty()) {
					System.out.println("No delivery partners available.");
				} else {
					partners.forEach(System.out::println);
				}
			}
			case 2 -> {
				System.out.print("Enter partner name: ");
				String name = scanner.nextLine();
				DeliveryPartner partner = new DeliveryPartner(IDGenerator.generatePartnerId(), name);
				partners.add(partner);
				System.out.println("Partner added.");
			}
			case 3 -> {
				if (partners.isEmpty()) {
					System.out.println("No delivery partners available to update.");
					break;
				}
				System.out.print("Enter Partner ID to update: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				DeliveryPartner partner = partners.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
				if (partner != null) {
					System.out.print("Enter new name: ");
					partner.setName(scanner.nextLine());
					System.out.println("Partner updated.");
				} else {
					System.out.println("Partner not found.");
				}
			}
			case 4 -> {
				if (partners.isEmpty()) {
					System.out.println("No delivery partners available to remove.");
					break;
				}
				System.out.print("Enter Partner ID to remove: ");
				int id = scanner.nextInt();
				boolean removed = partners.removeIf(p -> p.getId() == id);
				System.out.println(removed ? "Partner removed." : "Partner not found.");
			}
			case 0 -> {
				saveAll();
				return;
			}
			default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void manageDiscount(Scanner scanner) {
		System.out.println("\n--- Discount Management ---");
		System.out.println("Current Threshold: ₹" + discount.getThreshold());
		System.out.println("Current Discount: ₹" + discount.getAmount());
		System.out.print("Enter new threshold: ");
		while (!scanner.hasNextDouble()) {
			System.out.println("Invalid input. Please enter a numeric value for threshold.");
			scanner.nextLine();
			System.out.print("Enter new threshold: ");
		}
		discount.setThreshold(scanner.nextDouble());
		scanner.nextLine();
		System.out.print("Enter new discount amount: ");
		while (!scanner.hasNextDouble()) {
			System.out.println("Invalid input. Please enter a numeric value for discount amount.");
			scanner.nextLine();
			System.out.print("Enter new discount amount: ");
		}
		discount.setAmount(scanner.nextDouble());
		scanner.nextLine();
	}

	private void saveAll() {
		SerializationUtil.saveList(menu, MENU_FILE);
		SerializationUtil.saveList(partners, PARTNER_FILE);
		SerializationUtil.saveObject(discount, DISCOUNT_FILE);
		System.out.println("All data saved.");
	}

	public Set<String> getCustomCuisines() {
		return customCuisines;
	}

	public List<FoodItem> getMenu() {
		return menu;
	}

	public List<DeliveryPartner> getPartners() {
		return partners;
	}

	public Discount getDiscount() {
		return discount;
	}
}