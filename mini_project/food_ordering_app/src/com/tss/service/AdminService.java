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
			case 0 -> {
				saveAll();
				System.out.println("All data saved.");
			}
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
			String name = scanner.nextLine().trim();
			if (name.isEmpty())
				throw new AppException("Food item name cannot be empty.");
			String nameUpper = name.toUpperCase();
			if (menuRepository.getAll().stream().anyMatch(item -> item.getName().toUpperCase().equals(nameUpper))) {
				throw new AppException("Food item with name '" + name + "' already exists.");
			}
			double price = readDoubleInput(scanner, "Enter price: ");
			if (price <= 0)
				throw new AppException("Price must be positive.");
			String cuisine = selectCuisine(scanner);
			if (cuisine == null)
				return;

			FoodItem newItem = new FoodItem(IDGenerator.getInstance().generateFoodItemId(cuisine), name, price,
					cuisine);
			menuRepository.add(newItem);
			IDGenerator.getInstance().resetCuisineCounter(cuisine, menuRepository.getAll().stream()
					.filter(i -> i.getCuisine().equals(cuisine)).collect(Collectors.toList()));
			saveAll();
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

			List<FoodItem> items = menuRepository.getAll().stream()
					.filter(f -> f.getCuisine().equalsIgnoreCase(cuisine)).collect(Collectors.toList());
			if (items.isEmpty()) {
				System.out.println("No items available in " + cuisine + " cuisine.");
				return;
			}
			System.out.println("\nAvailable Food Items in " + cuisine + ":");
			printMenuTable(items, cuisine);

			int maxId = items.stream().mapToInt(FoodItem::getId).max().orElse(0);
			int id = readIntInput(scanner, "Enter Food ID to update: ", 1, maxId);
			if (id == -1)
				return;

			FoodItem item = items.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
			if (item == null) {
				throw new AppException("Food item not found with ID " + id + " in " + cuisine + " cuisine.");
			}

			System.out.print("New name (leave blank to keep current): ");
			String newName = scanner.nextLine().trim();
			if (!newName.isEmpty()) {
				item.setName(newName);
			}
			double newPrice = readDoubleInput(scanner, "New price (enter 0 to keep current): ");
			if (newPrice > 0) {
				item.setPrice(newPrice);
			}
			System.out.println("Select new cuisine (0 to keep current):");
			String newCuisine = selectCuisine(scanner);
			if (newCuisine != null) {
				item.setCuisine(newCuisine);
			}
			menuRepository.update(item);
			IDGenerator.getInstance().resetCuisineCounter(item.getCuisine(), menuRepository.getAll().stream()
					.filter(i -> i.getCuisine().equals(item.getCuisine())).collect(Collectors.toList()));
			if (!item.getCuisine().equalsIgnoreCase(cuisine)) {
				IDGenerator.getInstance().resetCuisineCounter(cuisine, menuRepository.getAll().stream()
						.filter(i -> i.getCuisine().equals(cuisine)).collect(Collectors.toList()));
			}
			saveAll();
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

			List<FoodItem> items = menuRepository.getAll().stream()
					.filter(f -> f.getCuisine().equalsIgnoreCase(cuisine)).collect(Collectors.toList());
			if (items.isEmpty()) {
				System.out.println("No items available in " + cuisine + " cuisine.");
				return;
			}
			System.out.println("\nAvailable Food Items in " + cuisine + ":");
			printMenuTable(items, cuisine);

			int maxId = items.stream().mapToInt(FoodItem::getId).max().orElse(0);
			int id = readIntInput(scanner, "Enter Food ID to remove: ", 1, maxId);
			if (id == -1)
				return;

			boolean removed = menuRepository.remove(id, cuisine);
			if (removed) {
				IDGenerator.getInstance().resetCuisineCounter(cuisine, menuRepository.getAll().stream()
						.filter(i -> i.getCuisine().equals(cuisine)).collect(Collectors.toList()));
				saveAll();
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
				String newCuisine = scanner.nextLine().trim().toUpperCase();
				if (newCuisine.isEmpty()) {
					System.out.println("Application Error: Cuisine name cannot be empty.");
				} else if (customCuisines.contains(newCuisine)) {
					System.out.println("Cuisine already exists.");
				} else {
					customCuisines.add(newCuisine);
					saveAll();
					System.out.println("Cuisine added: " + newCuisine);
				}
			}
			case 3 -> {
				List<String> cuisines = new ArrayList<>(customCuisines);
				if (cuisines.isEmpty()) {
					System.out.println("No cuisines available to remove.");
					break;
				}
				System.out.println("\nAvailable Cuisines:");
				for (int i = 0; i < cuisines.size(); i++) {
					System.out.println((i + 1) + ". " + cuisines.get(i));
				}
				int cuisineChoice = readIntInput(scanner, "Select cuisine to remove: ", 1, cuisines.size());
				if (cuisineChoice == -1)
					break;
				String toRemove = cuisines.get(cuisineChoice - 1);
				customCuisines.remove(toRemove);
				menuRepository.getAll().removeIf(item -> item.getCuisine().equalsIgnoreCase(toRemove));
				menuRepository.save();
				saveAll();
				System.out.println("Cuisine removed: " + toRemove);
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
					System.out.println("\nAvailable Delivery Partners:");
					printPartnerTable(partnerRepository.getAll());
				}
			}
			case 2 -> {
				System.out.print("Enter partner name: ");
				String name = scanner.nextLine().trim();
				if (name.isEmpty()) {
					System.out.println("Application Error: Partner name cannot be empty.");
					break;
				}
				String nameUpper = name.toUpperCase();
				if (partnerRepository.getAll().stream().anyMatch(p -> p.getName().toUpperCase().equals(nameUpper))) {
					System.out.println("Partner with name '" + name + "' already exists.");
					break;
				}
				DeliveryPartner partner = new DeliveryPartner(IDGenerator.getInstance().generatePartnerId(), name);
				partnerRepository.add(partner);
				IDGenerator.getInstance().resetPartnerCounter(partnerRepository.getAll());
				saveAll();
				System.out.println("Partner added.");
			}
			case 3 -> {
				if (partnerRepository.getAll().isEmpty()) {
					System.out.println("No delivery partners available to update.");
					break;
				}
				System.out.println("\nAvailable Delivery Partners:");
				printPartnerTable(partnerRepository.getAll());
				int maxId = partnerRepository.getAll().stream().mapToInt(DeliveryPartner::getId).max().orElse(0);
				int id = readIntInput(scanner, "Enter Partner ID to update: ", 1, maxId);
				if (id == -1)
					break;
				DeliveryPartner partner = partnerRepository.getAll().stream().filter(p -> p.getId() == id).findFirst()
						.orElse(null);
				if (partner != null) {
					System.out.print("Enter new name: ");
					String newName = scanner.nextLine().trim();
					if (newName.isEmpty()) {
						System.out.println("Application Error: Partner name cannot be empty.");
						break;
					}
					String newNameUpper = newName.toUpperCase();
					if (partnerRepository.getAll().stream()
							.anyMatch(p -> p.getId() != id && p.getName().toUpperCase().equals(newNameUpper))) {
						System.out.println("Partner with name '" + newName + "' already exists.");
						break;
					}
					partner.setName(newName);
					partnerRepository.update(partner);
					IDGenerator.getInstance().resetPartnerCounter(partnerRepository.getAll());
					saveAll();
					System.out.println("Partner updated.");
					break; // Break after successful update
				} else {
					System.out.println("Partner not found.");
				}
			}
			case 4 -> {
				if (partnerRepository.getAll().isEmpty()) {
					System.out.println("No delivery partners available to remove.");
					break;
				}
				System.out.println("\nAvailable Delivery Partners:");
				printPartnerTable(partnerRepository.getAll());
				int maxId = partnerRepository.getAll().stream().mapToInt(DeliveryPartner::getId).max().orElse(0);
				int id = readIntInput(scanner, "Enter Partner ID to remove: ", 1, maxId);
				if (id == -1)
					break;
				boolean removed = partnerRepository.remove(id);
				if (removed) {
					IDGenerator.getInstance().resetPartnerCounter(partnerRepository.getAll());
					saveAll();
					System.out.println("Partner removed.");
				} else {
					System.out.println("Partner not found.");
				}
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
			double newThreshold = readDoubleInput(scanner, "Enter new threshold: ");
			if (newThreshold < 0)
				throw new AppException("Threshold cannot be negative.");
			double newAmount = readDoubleInput(scanner, "Enter new discount amount: ");
			if (newAmount < 0)
				throw new AppException("Discount amount cannot be negative.");
			if (newAmount > newThreshold)
				throw new AppException("Discount amount cannot exceed the threshold.");
			discount.setThreshold(newThreshold);
			discount.setAmount(newAmount);
			saveAll();
			System.out.println("Discount updated.");
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void saveAll() {
		menuRepository.save();
		partnerRepository.save();
		SerializationUtil.saveObject(discount, DISCOUNT_FILE);
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

	private double readDoubleInput(Scanner scanner, String prompt) throws AppException {
		System.out.print(prompt);
		try {
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				throw new AppException("Input cannot be empty.");
			}
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new AppException("Invalid input. Please enter a numeric value.");
		}
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
		System.out.println("0. Back");
		int choice = readIntInput(scanner, "Enter choice: ", 0, cuisines.size());
		return choice == -1 || choice == 0 ? null : cuisines.get(choice - 1);
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

	private void printPartnerTable(List<DeliveryPartner> partners) {
		System.out.printf("+------+----------------------+%n");
		System.out.printf("| ID   | Name                 |%n");
		System.out.printf("+------+----------------------+%n");
		for (DeliveryPartner partner : partners) {
			System.out.printf("| %-4d | %-20s |%n", partner.getId(), partner.getName());
		}
		System.out.printf("+------+----------------------+%n");
	}
}