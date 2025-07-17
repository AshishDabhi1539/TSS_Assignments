package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private static int nextCartId = 1;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        item.setCartId(nextCartId++);
        items.add(item);
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public boolean removeItem(int cartId, int quantityToRemove) {
        for (OrderItem item : items) {
            if (item.getCartId() == cartId) {
                if (quantityToRemove >= item.getQuantity()) {
                    items.remove(item);
                } else {
                    item.setQuantity(item.getQuantity() - quantityToRemove);
                }
                reassignCartIds();
                return true;
            }
        }
        return false;
    }

    private void reassignCartIds() {
        int newCartId = 123;
        for (OrderItem item : items) {
            item.setCartId(newCartId++);
        }
        nextCartId = newCartId;
    }
}