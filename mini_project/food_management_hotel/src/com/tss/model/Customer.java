package com.tss.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String username;
    private String password;
    private double discountThreshold;

    public Customer(int id, String name, String username, String password) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.discountThreshold = 500.0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean authenticate(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    public double getDiscountThreshold() {
        return discountThreshold;
    }

    // Add method to allow threshold modification (e.g., by admin or loyalty program)
    public void setDiscountThreshold(double discountThreshold) {
        this.discountThreshold = discountThreshold;
    }
}