package com.tss.test;

import com.tss.model.CreditCardPayment;
import com.tss.model.IPayment;
import com.tss.model.ShoppingCart;
import com.tss.model.UPIPayment;

public class ShoppingCartTest {

	public static void main(String[] args) {
		IPayment payByCreditCard = new CreditCardPayment();
		IPayment payByUPI = new UPIPayment();

		ShoppingCart cart1 = new ShoppingCart(payByCreditCard);
		cart1.checkOut(500);

		ShoppingCart cart2 = new ShoppingCart(payByUPI);
		cart2.checkOut(1000);
	}

}
