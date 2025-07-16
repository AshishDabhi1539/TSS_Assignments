package com.tss.test;

import java.util.Properties;
import java.util.Scanner;

import com.tss.model.Admin;
import com.tss.model.Customer;
import com.tss.service.AdminService;
import com.tss.service.CustomerService;
import com.tss.service.DeliveryService;
import com.tss.service.DiscountService;
import com.tss.service.InvoiceService;
import com.tss.util.ConfigUtil;

public class FoodManagementTest {

    private static Admin admin;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        DiscountService discountService = new DiscountService(adminService.getDiscount());
        DeliveryService deliveryService = new DeliveryService(adminService.getPartners());
        InvoiceService invoiceService = new InvoiceService();

        Properties adminProps = ConfigUtil.loadProperties("admin.properties");
        String adminUser = adminProps.getProperty("username");
        String adminPass = adminProps.getProperty("password");

        admin = new Admin(adminUser, adminPass);

        try {
            while (true) {
                System.out.println("\n=== Mini Food Ordering System ===");
                System.out.println("1. Login as Admin");
                System.out.println("2. Order as Customer");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number (0, 1 or 2).");
                    scanner.nextLine();
                    continue;
                }
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> handleAdminLogin(scanner, adminService);
                    case 2 -> handleCustomerOrder(scanner, adminService, discountService, deliveryService, invoiceService);
                    case 0 -> {
                        System.out.println("Thank you for using our app!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static void handleAdminLogin(Scanner scanner, AdminService adminService) {
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        if (admin != null && admin.authenticate(user, pass)) {
            adminService.showAdminMenu(scanner);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void handleCustomerOrder(Scanner scanner, AdminService adminService, DiscountService discountService,
            DeliveryService deliveryService, InvoiceService invoiceService) {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.handleCustomerAuth(scanner);
        if (customer == null) {
            System.out.println("Customer authentication failed. Please sign up or try again.");
            return;
        }

        customerService.manageCustomerOrder(customer, adminService, scanner);

        System.out.println("Order process completed. Returning to main menu.");
    }
}