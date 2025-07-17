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
            case 3 -> removeItemFromOrder(order, scanner, menuService, adminService.getCustomCuisines());
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
        System.out.printf("\nAvailable Items in %s Cuisine:%n", selectedCuisine);
        System.out.printf("+------+----------------------+----------+%n");
        System.out.printf("| ID   | Name                 | Price ₹  |%n");
        System.out.printf("+------+----------------------+----------+%n");
        for (FoodItem item : items) {
            System.out.printf("| %-4d | %-20s | %-8.2f |%n", item.getId(), item.getName(), item.getPrice());
        }
        System.out.printf("+------+----------------------+----------+%n");

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
        order.addItem(new OrderItem(item, quantity, 0));
        System.out.println(quantity + " x " + item.getName() + " added to cart.");
    }

    private void removeItemFromOrder(Order order, Scanner scanner, MenuService menuService, Set<String> cuisines) {
        if (order.getItems().isEmpty()) {
            System.out.println("Cart is empty. No items to remove.");
            return;
        }
        System.out.println("\n--- Remove Item from Buy ---");
        System.out.printf("+-------+----------------------+----------+----------+----------+%n");
        System.out.printf("| CartID| Name                 | Quantity | Subtotal | Cuisine  |%n");
        System.out.printf("+-------+----------------------+----------+----------+----------+%n");
        for (OrderItem item : order.getItems()) {
            System.out.printf("| %-5d | %-20s | %-8d | ₹%-7.2f | %-8s |%n",
                    item.getCartId(),
                    item.getItem().getName(),
                    item.getQuantity(),
                    item.getSubtotal(),
                    item.getItem().getCuisine());
        }
        System.out.printf("+-------+----------------------+----------+----------+----------+%n");

        System.out.print("Enter Cart ID to remove: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        int cartId = scanner.nextInt();
        scanner.nextLine();

        OrderItem itemToRemove = order.getItems().stream()
                .filter(item -> item.getCartId() == cartId)
                .findFirst()
                .orElse(null);
        if (itemToRemove == null) {
            System.out.println("No item found with Cart ID " + cartId + " in cart.");
            return;
        }

        int maxQuantity = itemToRemove.getQuantity();
        if (maxQuantity > 1) {
            System.out.printf("Enter quantity to remove (1 to %d): ", maxQuantity);
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                return;
            }
            int quantityToRemove = scanner.nextInt();
            scanner.nextLine();
            if (quantityToRemove <= 0 || quantityToRemove > maxQuantity) {
                System.out.printf("Invalid quantity. Please enter a number between 1 and %d.%n", maxQuantity);
                return;
            }
            boolean removed = order.removeItem(cartId, quantityToRemove);
            if (removed) {
                if (quantityToRemove == maxQuantity) {
                    System.out.println("Item with Cart ID " + cartId + " (" + itemToRemove.getItem().getName() + ") removed from cart.");
                } else {
                    System.out.println(quantityToRemove + " x " + itemToRemove.getItem().getName() + " removed from cart.");
                }
            }
        } else {
            boolean removed = order.removeItem(cartId, 1);
            if (removed) {
                System.out.println("Item with Cart ID " + cartId + " (" + itemToRemove.getItem().getName() + ") removed from cart.");
            }
        }
    }

    private void printInvoice(Order order, DiscountService discountService, PaymentService paymentService, InvoiceService invoiceService, DeliveryService deliveryService, Scanner scanner) {
        if (order.getItems().isEmpty()) {
            System.out.println("Cart is empty. Nothing to invoice.");
            return;
        }

        double total = order.getTotal();
        double discountThreshold = order.getCustomer().getDiscountThreshold();
        if (discountThreshold < 0) {
            System.out.println("Invalid discount threshold. Contact support.");
            return;
        }

        double discount = (total > discountThreshold) ? discountService.getDiscountAmount(total) : 0.0;
        double payable = total - discount;

        int maxAttempts = 3;
        int attempts = 0;
        Payment payment = null;

        while (attempts < maxAttempts) {
            System.out.println("Available Payment Modes:");
            Payment.Mode[] modes = Payment.Mode.values();
            for (int i = 0; i < modes.length; i++) {
                System.out.println((i + 1) + ". " + modes[i]);
            }
            System.out.print("Choose Payment Mode (1-" + modes.length + "): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Attempts remaining: " + (maxAttempts - attempts));
                } else {
                    System.out.println("Too many invalid attempts. Returning to menu.");
                    return;
                }
                continue;
            }

            int paymentMode = scanner.nextInt();
            scanner.nextLine();
            if (paymentMode < 1 || paymentMode > modes.length) {
                System.out.println("Invalid payment mode. Please enter a number between 1 and " + modes.length + ".");
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Attempts remaining: " + (maxAttempts - attempts));
                } else {
                    System.out.println("Too many invalid attempts. Returning to menu.");
                    return;
                }
                continue;
            }

            Payment.Mode mode = modes[paymentMode - 1];
            if (mode == Payment.Mode.UPI) {
                int pinAttempts = 0;
                boolean validPin = false;
                while (pinAttempts < maxAttempts) {
                    System.out.print("Enter 4-digit UPI PIN: ");
                    String pinInput = scanner.nextLine().trim();
                    if (pinInput.matches("\\d{4}")) {
                        int pin = Integer.parseInt(pinInput);
                        if (pin >= 0 && pin <= 9999) {
                            validPin = true;
                            break;
                        } else {
                            System.out.println("PIN must be a 4-digit number between 0000 and 9999.");
                        }
                    } else {
                        System.out.println("Invalid PIN. Please enter exactly 4 digits.");
                    }
                    pinAttempts++;
                    if (pinAttempts < maxAttempts) {
                        System.out.println("PIN attempts remaining: " + (maxAttempts - pinAttempts));
                    } else {
                        System.out.println("Too many invalid PIN attempts. Returning to menu.");
                        return;
                    }
                }
                if (!validPin) {
                    return;
                }
            }

            payment = new Payment(mode, payable);
            break;
        }

        if (payment != null) {
            DeliveryPartner partner = deliveryService.assignPartner();
            if (partner.getId() == 0) {
                System.out.println("Warning: No delivery partner available. Order will be processed without delivery.");
            }
            invoiceService.printInvoice(order, discount, payment, partner);
        }
    }

    private void saveCustomers() {
        SerializationUtil.saveList(customers, CUSTOMER_FILE);
    }
}