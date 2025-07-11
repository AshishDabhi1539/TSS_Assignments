package com.tss.structural.adapter.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<IItems> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addItemsToCart(List<IItems> items) {
        cartItems.addAll(items);
    }

    public List<IItems> getCartItems() {
        return cartItems;
    }

    public double getCartPrice() {
        double total = 0;
        for (IItems item : cartItems) {
            total += item.getItemPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.printf("\n%-15s %-10s%n", "Item Name", "Price");
        for (IItems item : cartItems) {
            System.out.printf("%-15s %.2f%n", item.getItemName(), item.getItemPrice());
        }
        System.out.println("\nTotal: " + getCartPrice());
    }
}
