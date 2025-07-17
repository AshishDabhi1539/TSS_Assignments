package com.tss.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.tss.model.DeliveryPartner;
import com.tss.model.Order;
import com.tss.model.OrderItem;
import com.tss.model.Payment;

public class InvoiceService {
    public void printInvoice(Order order, double discountAmount, Payment payment, DeliveryPartner partner) {
        // Get current date and time for the invoice
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Print invoice header
        System.out.println("\n══════════════════════════════════════");
        System.out.println("           MINI FOOD ORDERING SYSTEM   ");
        System.out.println("══════════════════════════════════════");
        System.out.println("Invoice Date: " + now.format(formatter));
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName() + " (" + order.getCustomer().getUsername() + ")");
        System.out.println("──────────────────────────────────────");

        // Print item details in a table
        System.out.println("Items Ordered:");
        System.out.printf("| %-4s | %-20s | %-8s | %-10s | %-10s |%n", "ID", "Item Name", "Qty", "Unit Price", "Subtotal");
        System.out.println("|──────|──────────────────────|──────────|────────────|────────────|");
        List<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            System.out.printf("| %-4d | %-20s | %-8d | ₹%-9.2f | ₹%-9.2f |%n",
                    item.getItem().getId(),
                    item.getItem().getName(),
                    item.getQuantity(),
                    item.getItem().getPrice(),
                    item.getSubtotal());
        }
        System.out.println("|──────|──────────────────────|──────────|────────────|────────────|");

        // Print totals section
        double total = order.getTotal();
        System.out.println("\nOrder Summary:");
        System.out.printf("  Subtotal:         ₹%-9.2f%n", total);
        System.out.printf("  Discount Applied: ₹%-9.2f%n", discountAmount);
        System.out.printf("  Total Payable:    ₹%-9.2f%n", (total - discountAmount));
        System.out.println("──────────────────────────────────────");

        // Print payment and delivery details
        System.out.println("Payment Details:");
        System.out.println("  " + payment);
        System.out.println("Delivery Details:");
        System.out.println("  " + (partner.getId() == 0 ? "No Delivery Partner Assigned" : partner));
        System.out.println("══════════════════════════════════════");
        System.out.println("Thank you for your order!");
        System.out.println("══════════════════════════════════════\n");
    }
}
