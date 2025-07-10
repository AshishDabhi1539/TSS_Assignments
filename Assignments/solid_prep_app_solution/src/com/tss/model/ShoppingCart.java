package com.tss.model;

public class ShoppingCart {

    private IPayment payment;

    public ShoppingCart(IPayment payment) {
        this.payment = payment;
    }

    public void checkOut(int amount) {
        payment.pay(amount);
    }
}
