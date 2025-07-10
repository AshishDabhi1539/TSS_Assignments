package com.tss.mode;

public class ShoppingCart {

    private CreditCardPayment payment = new CreditCardPayment();

    public void checkOut(int amount) {
        payment.payment(amount);
    }
}
