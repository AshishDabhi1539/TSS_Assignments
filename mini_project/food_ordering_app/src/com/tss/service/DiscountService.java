package com.tss.service;

import com.tss.model.Discount;

public class DiscountService {
    private Discount discount;

    /**
     * Default constructor that initializes with a default discount (threshold: 500, amount: 50).
     */
    public DiscountService() {
        this.discount = new Discount(500, 50);
    }

    /**
     * Constructor that initializes with a provided discount.
     * @param discount The discount object to use, or a default if null.
     */
    public DiscountService(Discount discount) {
        this.discount = discount != null ? discount : new Discount(500, 50);
    }

    /**
     * Sets the discount object.
     * @param discount The discount to set.
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Gets the discount amount for a given total.
     * @param total The total order amount.
     * @return The discount amount.
     */
    public double getDiscountAmount(double total) {
        return discount.applyDiscount(total);
    }
}