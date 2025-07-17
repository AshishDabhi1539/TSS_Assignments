package com.tss.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

public class FoodItem implements Comparable<FoodItem>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double price;
    private String cuisine;

    public FoodItem(int id, String name, double price, String cuisine) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(FoodItem other) {
        return Double.compare(this.price, other.price);
    }

    /**
     * Creates a comparator for sorting by name.
     * @return A comparator for FoodItem names.
     */
    public static Comparator<FoodItem> nameComparator() {
        return Comparator.comparing(FoodItem::getName);
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + price + " rupees (" + cuisine + ")";
    }
}