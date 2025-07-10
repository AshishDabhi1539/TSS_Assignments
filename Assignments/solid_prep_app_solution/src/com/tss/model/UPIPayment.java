package com.tss.model;

public class UPIPayment implements IPayment {

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using UPI");
    }
}