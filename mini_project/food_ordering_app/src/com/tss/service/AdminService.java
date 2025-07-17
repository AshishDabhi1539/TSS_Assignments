package com.tss.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.tss.exception.AppException;
import com.tss.model.DeliveryPartner;
import com.tss.model.Discount;
import com.tss.model.FoodItem;
import com.tss.repository.MenuRepository;
import com.tss.repository.PartnerRepository;
import com.tss.util.IDGenerator;
import com.tss.util.SerializationUtil;

public class AdminService {
	private final MenuRepository menuRepository;
	private final PartnerRepository partnerRepository;
	private final Set<String> customCuisines;
	private Discount discount;
	private static final String DISCOUNT_FILE = "discount.ser";

	public AdminService() {
		this.menuRepository = new MenuRepository();
		this.partnerRepository = new PartnerRepository();
		this.customCuisines = new HashSet<>(Arrays.asList("INDIAN", "ITALIAN"));
		this.discount = SerializationUtil.readObject(DISCOUNT_FILE);
		if (discount == null) {
			discount = new Discount(500, 50);
		}
		IDGenerator.getInstance().loadInitialCounters(menuRepository.getAll(), partnerRepository.getAll(),
				new ArrayList<>());
	}

	/**
	 * Displays and handles the admin menu.
	 * 
	 * @param scanner The scanner for user input.
	 */
	public void showAdminMenu(Scanner scanner) {
		int choice;
		do {
			System.out.println("\n--- Admin Panel ---");
			System.out.println("1. Manage Menu");
			System.out.println("2. Manage Delivery Partners");
			System.out.println("3. Manage Discount");
			System.out.println("0. Exit Admin Panel");
			choice = readIntInput(scanner, "Enter choice: ", 0, 3);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1 -> manageMenu(scanner);
			case 2 -> managePartners(scanner);
			case 3 -> manageDiscount(scanner);
			case 0 -> saveAll();
			}
		} while (choice != 0);
	}

	private void manageMenu(Scanner scanner) {
		int choice;
		do {
			System.out.println("\n--- Menu Management ---");
			System.out.println("1. View Menu by Cuisine");
			System.out.println("2. Add Food Item");
			System.out.println("3. Update Food Item");
			System.out.println("4. Remove Food Item");
			System.out.println("5. Manage Cuisine");
			System.out.println("0. Back");
			choice = readIntInput(scanner, "Enter choice: ", 0, 5);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1 -> viewMenuByCuisine(scanner);
			case 2 -> addFoodItem(scanner);
			case 3 -> updateFoodItem(scanner);
			case 4 -> removeFoodItem(scanner);
			case 5 -> manageCuisine(scanner);
			case 0 -> saveAll();
			}
		} while (choice != 0);
	}

	private void viewMenuByCuisine(Scanner scanner) {
		List<String> cuisines = new ArrayList<>(customCuisines);
		if (cuisines.isEmpty()) {
			System.out.println("No cuisines available.");
			return;
		}
		System.out.println("\n--- View Menu by Cuisine ---");
		for (int i = 0; i < cuisines.size(); i++) {
			System.out.println((i + 1) + ". " + cuisines.get(i));
		}
		System.out.println("0. Back");
		int choice = readIntInput(scanner, "Enter choice: ", 0, cuisines.size());
		if (choice == -1 || choice == 0)
			return;

		String cuisineName = cuisines.get(choice - 1);
		List<FoodItem> filtered = menuRepository.getAll().stream()
				.filter(f -> f.getCuisine().equalsIgnoreCase(cuisineName)).collect(Collectors.toList());
		printMenuTable(filtered, cuisineName);
	}

	private void addFoodItem(Scanner scanner) {
		try {
			System.out.print("Enter name: ");
			String name = scanner.nextLine();
			double price = readDoubleInput(scanner, "Enter price: ");
			String cuisine = selectCuisine(scanner);
			if (cuisine == null)
				return;

			FoodItem newItem = new FoodItem(IDGenerator.getInstance().generateFoodItemId(cuisine), name, price,
					cuisine);
			menuRepository.add(newItem);
			IDGenerator.getInstance().resetCuisineCounter(cuisine, menuRepository.getAll().stream()
					.filter(i -> i.getCuisine().equals(cuisine)).collect(Collectors.toList()));
			System.out.println("Food item added.");
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateFoodItem(Scanner scanner) {
		if (menuRepository.getAll().isEmpty()) {
			System.out.println("No items available to update.");
			return;
		}
		try {
			String cuisine = selectCuisine(scanner);
			if (cuisine == null)
				return;

			System.out.print("Enter Food ID to update: ");
			int id = readIntInput(scanner, "Enter Food ID to update: ", 1, Integer.MAX_VALUE);
			if (id == -1)
				return;

			FoodItem item = menuRepository.getAll().stream()
					.filter(f -> f.getId() == id && f.getCuisine().equalsIgnoreCase(cuisine)).findFirst().orElse(null);
			if (item == null) {
				throw new AppException("Food item not found with ID " + id + " in " + cuisine + " cuisine.");
			}

			System.out.print("New name: ");
			item.setName(scanner.nextLine());
			item.setPrice(readDoubleInput(scanner, "New price: "));
			String newCuisine = selectCuisine(scanner);
			if (newCuisine != null) {
				item.setCuisine(newCuisine);
			}
			menuRepository.update(item);
			IDGenerator.getInstance().resetCuisineCounter(item.getCuisine(), menuRepository.getAll().stream()
					.filter(i -> i.getCuisine().equals(item.getCuisine())).collect(Collectors.toList()));
			System.out.println("Item updated.");
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void removeFoodItem(Scanner scanner) {
		if (menuRepository.getAll().isEmpty()) {
			System.out.println("No items available to remove.");
			return;
		}
		try {
			String cuisine = selectCuisine(scanner);
			if (cuisine == null)
				return;

			int id = readIntInput(scanner, "Enter Food ID to remove: ", 1, Integer.MAX_VALUE);
			if (id == -1)
				return;

			boolean removed = menuRepository.remove(id, cuisine);
			if (removed) {
				IDGenerator.getInstance().resetCuisineCounter(cuisine, menuRepository.getAll().stream()
						.filter(i -> i.getCuisine().equals(cuisine)).collect(Collectors.toList()));
				System.out.println("Item removed.");
			} else {
				throw new AppException("No item found with ID " + id + " in " + cuisine + " cuisine.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void manageCuisine(Scanner scanner) {
		int choice;
		do {
			System.out.println("\n--- Manage Cuisine ---");
			System.out.println("1. View All Cuisines");
			System.out.println("2. Add New Cuisine");
			System.out.println("3. Remove Cuisine");
			System.out.println("0. Back");
			choice = readIntInput(scanner, "Enter choice: ", 0, 3);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1 -> customCuisines.forEach(c -> System.out.println("- " + c));
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
			case 0 -> saveAll();
			}
		} while (choice != 0);
	}

	private void managePartners(Scanner scanner) {
		int choice;
		do {
			System.out.println("\n--- Delivery Partner Management ---");
			System.out.println("1. View Partners");
			System.out.println("2. Add Partner");
			System.out.println("3. Update Partner");
			System.out.println("4. Remove Partner");
			System.out.println("0. Back");
			choice = readIntInput(scanner, "Enter choice: ", 0, 4);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1 -> {
				if (partnerRepository.getAll().isEmpty()) {
					System.out.println("No delivery partners available.");
				} else {
					partnerRepository.getAll().forEach(System.out::println);
				}
			}
			case 2 -> {
				System.out.print("Enter partner name: ");
				String name = scanner.nextLine();
				DeliveryPartner partner = new DeliveryPartner(IDGenerator.getInstance().generatePartnerId(), name);
				partnerRepository.add(partner);
				IDGenerator.getInstance().resetPartnerCounter(partnerRepository.getAll());
				System.out.println("Partner added.");
			}
			case 3 -> {
				if (partnerRepository.getAll().isEmpty()) {
					System.out.println("No delivery partners available to update.");
					break;
				}
				int id = readIntInput(scanner, "Enter Partner ID to update: ", 1, Integer.MAX_VALUE);
				if (id == -1)
					break;
				DeliveryPartner partner = partnerRepository.getAll().stream().filter(p -> p.getId() == id).findFirst()
						.orElse(null);
				if (partner != null) {
					System.out.print("Enter new name: ");
					partner.setName(scanner.nextLine());
					partnerRepository.update(partner);
					System.out.println("Partner updated.");
				} else {
					System.out.println("Partner not found.");
				}
				IDGenerator.getInstance().resetPartnerCounter(partnerRepository.getAll());
			}
			case 4 -> {
				if (partnerRepository.getAll().isEmpty()) {
					System.out.println("No delivery partners available to remove.");
					break;
				}
				int id = readIntInput(scanner, "Enter Partner ID to remove: ", 1, Integer.MAX_VALUE);
				if (id == -1)
					break;
				boolean removed = partnerRepository.remove(id);
				System.out.println(removed ? "Partner removed." : "Partner not found.");
				IDGenerator.getInstance().resetPartnerCounter(partnerRepository.getAll());
			}
			case 0 -> saveAll();
			}
		} while (choice != 0);
	}

	private void manageDiscount(Scanner scanner) {
		try {
			System.out.println("\n--- Discount Management ---");
			System.out.println("Current Threshold: ₹" + discount.getThreshold());
			System.out.println("Current Discount: ₹" + discount.getAmount());
			discount.setThreshold(readDoubleInput(scanner, "Enter new threshold: "));
			discount.setAmount(readDoubleInput(scanner, "Enter new discount amount: "));
			SerializationUtil.saveObject(discount, DISCOUNT_FILE);
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void saveAll() {
		menuRepository.save();
		partnerRepository.save();
		SerializationUtil.saveObject(discount, DISCOUNT_FILE);
		System.out.println("All data saved.");
	}

	public Set<String> getCustomCuisines() {
		return customCuisines;
	}

	public List<FoodItem> getMenu() {
		return menuRepository.getAll();
	}

	public List<DeliveryPartner> getPartners() {
		return partnerRepository.getAll();
	}

	public Discount getDiscount() {
		return discount;
	}

	private int readIntInput(Scanner scanner, String prompt, int min, int max) {
		System.out.print(prompt);
		if (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
			scanner.nextLine();
			return -1;
		}
		int choice = scanner.nextInt();
		scanner.nextLine();
		if (choice < min || choice > max) {
			System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
			return -1;
		}
		return choice;
	}

	private double readDoubleInput(Scanner scanner, String prompt) throws AppException {
		System.out.print(prompt);
		if (!scanner.hasNextDouble()) {
			scanner.nextLine();
			throw new AppException("Invalid input. Please enter a numeric value.");
		}
		double value = scanner.nextDouble();
		scanner.nextLine();
		if (value < 0) {
			throw new AppException("Value must be non-negative.");
		}
		return value;
	}

	private String selectCuisine(Scanner scanner) {
		List<String> cuisines = new ArrayList<>(customCuisines);
		if (cuisines.isEmpty()) {
			System.out.println("No cuisines available.");
			return null;
		}
		System.out.println("Available Cuisines:");
		for (int i = 0; i < cuisines.size(); i++) {
			System.out.println((i + 1) + ". " + cuisines.get(i));
		}
		int choice = readIntInput(scanner, "Enter choice: ", 1, cuisines.size());
		return choice == -1 ? null : cuisines.get(choice - 1);
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