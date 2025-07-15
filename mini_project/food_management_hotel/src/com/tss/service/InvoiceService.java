package com.tss.service;

import java.util.List;

import com.tss.model.DeliveryPartner;
import com.tss.model.Order;
import com.tss.model.OrderItem;
import com.tss.model.Payment;

public class InvoiceService {
	public void printInvoice(Order order, double discountAmount, Payment payment, DeliveryPartner partner) {
        System.out.println("\n=========== INVOICE ===========");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Order ID: " + order.getOrderId());
        List<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            System.out.println(item.getItem().getName() + " x " + item.getQuantity() + " = ₹" + item.getSubtotal());
        }
        System.out.println("------------------------------");
        double total = order.getTotal();
        System.out.println("Total: ₹" + total);
        System.out.println("Discount: ₹" + discountAmount);
        System.out.println("Payable: ₹" + (total - discountAmount));
        System.out.println(payment);
        System.out.println("Delivery Partner: " + partner);
        System.out.println("===============================");
    }
}
