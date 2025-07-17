package com.tss.model;

import java.io.Serial;
import java.io.Serializable;

public class Discount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private double threshold;
    private double amount;

    public Discount(double threshold, double amount) {
        this.threshold = threshold;
        this.amount = amount;
    }

    /**
     * Applies the discount to a total amount.
     * @param total The total order amount.
     * @return The discount amount if applicable, 0 otherwise.
     */
    public double applyDiscount(double total) {
        return total > threshold ? amount : 0;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}