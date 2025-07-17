package com.tss.model;

public class Payment {
    public enum Mode {
        CASH, UPI
    }

    private final Mode mode;
    private final double amount;

    public Payment(Mode mode, double amount) {
        this.mode = mode;
        this.amount = amount;
    }

    public Mode getMode() {
        return mode;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Paid " + amount + " rupees via " + mode;
    }
}