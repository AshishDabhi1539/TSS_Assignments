package com.tss.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tss.model.DeliveryPartner;
import com.tss.model.Order;
import com.tss.model.OrderItem;
import com.tss.model.Payment;

public class InvoiceService {
	/**
	 * Prints the invoice for an order.
	 * @param order The order to print.
	 * @param discount The discount applied.
	 * @param payment The payment details.
	 * @param partner The assigned delivery partner.
	 */
	public void printInvoice(Order order, double discount, Payment payment, DeliveryPartner partner) {
	    System.out.println("\n══════════════════════════════════════");
	    System.out.println("           MINI FOOD ORDERING SYSTEM   ");
	    System.out.println("══════════════════════════════════════");
	    System.out.println("Invoice Date: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
	    System.out.println("Order ID: " + order.getOrderId());
	    System.out.println("Customer: " + order.getCustomer().getName() + " (" + order.getCustomer().getUsername() + ")");
	    System.out.println("──────────────────────────────────────");
	    System.out.println("Items Ordered:");
	    System.out.printf("| %-20s | %-8s | %-10s | %-10s |%n", "Item Name", "Qty", "Unit Price", "Subtotal");
	    System.out.println("|──────────────────────|──────────|────────────|────────────|");
	    for (OrderItem item : order.getItems()) {
	        System.out.printf("| %-20s | %-8d | ₹%-9.2f | ₹%-9.2f |%n",
	                item.getItem().getName(), item.getQuantity(),
	                item.getItem().getPrice(), item.getSubtotal());
	    }
	    System.out.println("|──────────────────────|──────────|────────────|────────────|");
	    System.out.println("\nOrder Summary:");
	    System.out.printf("  Subtotal:         ₹%.2f   %n", order.getTotal());
	    System.out.printf("  Discount Applied: ₹%.2f   %n", discount);
	    System.out.printf("  Total Payable:    ₹%.2f   %n", order.getTotal() - discount);
	    System.out.println("──────────────────────────────────────");
	    System.out.println("Payment Details:");
	    System.out.println("  Paid " + payment.getAmount() + " rupees via " + payment.getMode());
	    System.out.println("Delivery Details:");
	    System.out.println("  " + (partner.getId() == 0 ? "No delivery partner assigned" : partner.getName() + " (ID: " + partner.getId() + ")"));
	    if (order.getStarRating() > 0) {
	        System.out.println("──────────────────────────────────────");
	        System.out.println("Customer Feedback:");
	        System.out.printf("  Rating: %s%n", "★".repeat(order.getStarRating()) + "☆".repeat(5 - order.getStarRating()));
	        if (!order.getFeedbackNote().isEmpty()) {
	            System.out.println("  Note: " + order.getFeedbackNote());
	        }
	    }
	    System.out.println("══════════════════════════════════════");
	    System.out.println("Thank you for your order!");
	    System.out.println("══════════════════════════════════════");
	}
}