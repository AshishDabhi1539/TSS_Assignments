package com.tss.model;

public class CreditCardPayment implements IPayment {

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using Credit Card");
    }
}