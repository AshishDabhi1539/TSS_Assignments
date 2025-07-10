package com.tss.model;

public class ProductClass {
    private int id;
    private String name;
    private double price;
    private double discountPercent;

    public ProductClass(int id, String name, double price, double discountPercent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public double calculateDiscountPrice() {
        return price - (price * discountPercent / 100);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}
