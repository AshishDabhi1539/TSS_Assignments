package com.tss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.tss.model.Customer;
import com.tss.model.DeliveryPartner;
import com.tss.model.FoodItem;
import com.tss.model.Order;
import com.tss.model.OrderItem;
import com.tss.model.Payment;
import com.tss.util.IDGenerator;
import com.tss.util.SerializationUtil;

public class CustomerService {
    private final String CUSTOMER_FILE = "customers.ser";
    private List<Customer> customers;

    public CustomerService() {
        customers = SerializationUtil.readList(CUSTOMER_FILE);
        if (customers == null)
            customers = new ArrayList<>();
        IDGenerator.loadInitialCounters(new ArrayList<>(), new ArrayList<>(), customers); // Initialize customer counter
    }

    public Customer handleCustomerAuth(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Choose option: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine();
                continue;
            }
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
            case 1 -> {
                System.out.print("Enter username: ");
                String user = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();

                if (user.trim().isEmpty() || pass.trim().isEmpty()) {
                    System.out.println("Username or password cannot be empty.");
                    break;
                }

                Customer customer = customers.stream().filter(c -> c.authenticate(user, pass)).findFirst().orElse(null);
                if (customer != null) {
                    System.out.println("Login successful! Welcome " + customer.getName());
                    return customer;
                } else {
                    System.out.println("Invalid credentials.");
                }
            }
            case 2 -> {
                System.out.print("Enter your full name: ");
                String name = scanner.nextLine();
                System.out.print("Choose username: ");
                String user = scanner.nextLine();
                System.out.print("Choose password: ");
                String pass = scanner.nextLine();

                if (user.trim().isEmpty() || pass.trim().isEmpty()) {
                    System.out.println("Username or password cannot be empty.");
                    break;
                }

                boolean exists = customers.stream().anyMatch(c -> c.getUsername().equals(user));
                if (exists) {
                    System.out.println("Username already taken. Try again.");
                } else {
                    Customer newCustomer = new Customer(IDGenerator.generateCustomerId(), name, user, pass);
                    customers.add(newCustomer);
                    IDGenerator.resetCustomerCounter(customers);
                    saveCustomers();
                    System.out.println("Registration successful. You can now login.");
                }
            }
            default -> System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    public void manageCustomerOrder(Customer customer, AdminService adminService, Scanner scanner) {
        Order order = new Order(IDGenerator.generateOrderId(), customer);
        MenuService menuService = new MenuService(adminService.getMenu());
        DiscountService discountService = new DiscountService(adminService.getDiscount());
        InvoiceService invoiceService = new InvoiceService();
        PaymentService paymentService = new PaymentService();
        DeliveryService deliveryService = new DeliveryService(adminService.getPartners());

        int choice = -1;
        do {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Menu by Cuisine");
            System.out.println("2. Add Item to Buy");
            System.out.println("3. Remove Item from Buy");
            System.out.println("4. Print Invoice");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (0-4).");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1 -> viewMenuByCuisine(menuService, adminService.getCustomCuisines(), scanner);
            case 2 -> addItemToOrder(order, menuService, adminService.getCustomCuisines(), scanner);
            case 3 -> removeItemFromOrder(order, scanner);
            case 4 -> printInvoice(order, discountService, paymentService, invoiceService, deliveryService, scanner);
            case 0 -> System.out.println("Exiting Customer Menu...");
            default -> System.out.println("Invalid choice. Please enter 0-4.");
            }
        } while (choice != 0);
    }

    private void viewMenuByCuisine(MenuService menuService, Set<String> cuisines, Scanner scanner) {
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
        System.out.print("Enter choice: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) return;
        if (choice < 1 || choice > cuisineList.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        String cuisine = cuisineList.get(choice - 1);
        List<FoodItem> items = menuService.getByCuisine(cuisine);
        if (items.isEmpty()) {
            System.out.println("No items in " + cuisine + " cuisine.");
        } else {
            System.out.printf("+------+----------------------+----------+%n");
            System.out.printf("| ID   | Name                 | Price ₹  |%n");
            System.out.printf("+------+----------------------+----------+%n");
            for (FoodItem item : items) {
                System.out.printf("| %-4d | %-20s | %-8.2f |%n", item.getId(), item.getName(), item.getPrice());
            }
            System.out.printf("+------+----------------------+----------+%n");
        }
    }

    private void addItemToOrder(Order order, MenuService menuService, Set<String> cuisines, Scanner scanner) {
        List<String> cuisineList = new ArrayList<>(cuisines);
        if (cuisineList.isEmpty()) {
            System.out.println("No cuisines available.");
            return;
        }
        System.out.println("\n--- Add Item to Buy ---");
        for (int i = 0; i < cuisineList.size(); i++) {
            System.out.println((i + 1) + ". " + cuisineList.get(i));
        }
        System.out.print("Select cuisine: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        int cuisineChoice = scanner.nextInt();
        scanner.nextLine();
        if (cuisineChoice < 1 || cuisineChoice > cuisineList.size()) {
            System.out.println("Invalid cuisine choice.");
            return;
        }
        String selectedCuisine = cuisineList.get(cuisineChoice - 1);
        List<FoodItem> items = menuService.getByCuisine(selectedCuisine);
        if (items.isEmpty()) {
            System.out.println("No items available in " + selectedCuisine + " cuisine.");
            return;
        }
        System.out.print("Enter Food Item ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        int itemId = scanner.nextInt();
        scanner.nextLine();
        FoodItem item = items.stream().filter(i -> i.getId() == itemId).findFirst().orElse(null);
        if (item == null) {
            System.out.println("Invalid Food Item ID for " + selectedCuisine + " cuisine.");
            return;
        }
        System.out.print("Enter Quantity: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }
        order.addItem(new OrderItem(item, quantity));
        System.out.println(quantity + " x " + item.getName() + " added to cart.");
    }

    private void removeItemFromOrder(Order order, Scanner scanner) {
        if (order.getItems().isEmpty()) {
            System.out.println("Cart is empty. No items to remove.");
            return;
        }
        System.out.println("\n--- Remove Item from Buy ---");
        System.out.println("Cart Items:");
        for (OrderItem item : order.getItems()) {
            System.out.println(item.getItem().getId() + ". " + item.getItem().getName() + " x " + item.getQuantity() + " - ₹" + item.getSubtotal());
        }
        System.out.print("Enter Food Item ID to remove: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        int itemId = scanner.nextInt();
        scanner.nextLine();
        boolean removed = order.removeItem(itemId);
        if (removed) {
            System.out.println("Item with ID " + itemId + " removed from cart.");
        } else {
            System.out.println("No item found with ID " + itemId + " in cart.");
        }
    }

    private void printInvoice(Order order, DiscountService discountService, PaymentService paymentService, InvoiceService invoiceService, DeliveryService deliveryService, Scanner scanner) {
        if (order.getItems().isEmpty()) {
            System.out.println("Cart is empty. Nothing to invoice.");
            return;
        }
        double total = order.getTotal();
        double discount = (total > order.getCustomer().getDiscountThreshold()) ? discountService.getDiscountAmount(total) : 0.0;
        double payable = total - discount;
        System.out.print("Choose Payment Mode (1. Cash, 2. UPI): ");
        if (scanner.hasNextInt()) {
            int paymentMode = scanner.nextInt();
            scanner.nextLine();
            if (paymentMode == 1 || paymentMode == 2) {
                Payment.Mode mode = (paymentMode == 1) ? Payment.Mode.CASH : Payment.Mode.UPI;
                Payment payment = new Payment(mode, payable);
                DeliveryPartner partner = deliveryService.assignPartner();
                invoiceService.printInvoice(order, discount, payment, partner);
            } else {
                System.out.println("Invalid payment mode. Please enter 1 or 2.");
            }
        } else {
            System.out.println("Invalid input. Please enter 1 or 2.");
            scanner.nextLine();
        }
    }

    private void saveCustomers() {
        SerializationUtil.saveList(customers, CUSTOMER_FILE);
    }
}