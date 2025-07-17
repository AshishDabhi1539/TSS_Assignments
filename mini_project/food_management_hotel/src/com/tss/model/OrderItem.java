package com.tss.model;

public class OrderItem {
    private int cartId;
    private FoodItem item;
    private int quantity;

    public OrderItem(FoodItem item, int quantity, int cartId) {
        this.item = item;
        this.quantity = quantity;
        this.cartId = cartId;
    }

    public FoodItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return item.getPrice() * quantity;
    }
}