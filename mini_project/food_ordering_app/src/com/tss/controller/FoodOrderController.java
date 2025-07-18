package com.tss.controller;

import java.util.Properties;
import java.util.Scanner;

import com.tss.config.ConfigService;
import com.tss.exception.AppException;
import com.tss.model.Admin;
import com.tss.model.Customer;
import com.tss.service.AdminService;
import com.tss.service.CustomerService;
import com.tss.service.DeliveryService;
import com.tss.service.DiscountService;
import com.tss.service.InvoiceService;

public class FoodOrderController {
    private final AdminService adminService;
    private final CustomerService customerService;
    @SuppressWarnings("unused")
	private final DiscountService discountService;
    @SuppressWarnings("unused")
	private final DeliveryService deliveryService;
    @SuppressWarnings("unused")
	private final InvoiceService invoiceService;
    private final Admin admin;

    public FoodOrderController() throws AppException {
        this.adminService = new AdminService();
        this.discountService = new DiscountService();
        this.deliveryService = new DeliveryService(adminService.getPartners());
        this.invoiceService = new InvoiceService();
        this.customerService = new CustomerService();
        Properties adminProps = ConfigService.getInstance().loadProperties("admin.properties");
        String adminUser = adminProps.getProperty("username");
        String adminPass = adminProps.getProperty("password");
        if (adminUser == null || adminUser.trim().isEmpty() || adminPass == null || adminPass.trim().isEmpty()) {
            throw new AppException("Admin username or password not found in admin.properties.");
        }
        this.admin = new Admin(adminUser, adminPass);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMainMenu();
                int choice = readIntInput(scanner, "Choose an option: ", 0, 2);
                if (choice == -1) continue;

                switch (choice) {
                    case 1 -> handleAdminLogin(scanner);
                    case 2 -> handleCustomerOrder(scanner);
                    case 0 -> {
                        System.out.println("Thank you for using our app!");
                        return;
                    }
                }
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Mini Food Ordering System ===");
        System.out.println("1. Login as Admin");
        System.out.println("2. Order as Customer");
        System.out.println("0. Exit");
    }

    private void handleAdminLogin(Scanner scanner) {
        try {
            System.out.print("Enter username: ");
            String user = scanner.nextLine().trim();
            if (user.isEmpty()) throw new AppException("Username cannot be empty.");
            System.out.print("Enter password: ");
            String pass = scanner.nextLine().trim();
            if (pass.isEmpty()) throw new AppException("Password cannot be empty.");

            if (admin.authenticate(user, pass)) {
                adminService.showAdminMenu(scanner);
            } else {
                throw new AppException("Invalid admin credentials.");
            }
        } catch (AppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleCustomerOrder(Scanner scanner) {
        try {
            Customer customer = customerService.handleCustomerAuth(scanner);
            if (customer == null) {
                throw new AppException("Customer authentication failed. Please sign up or try again.");
            }
            customerService.manageCustomerOrder(customer, adminService, scanner);
            System.out.println("Order process completed. Returning to main menu.");
        } catch (AppException e) {
            System.out.println(e.getMessage());
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

    public static void main(String[] args) throws AppException {
        new FoodOrderController().start();
    }
}