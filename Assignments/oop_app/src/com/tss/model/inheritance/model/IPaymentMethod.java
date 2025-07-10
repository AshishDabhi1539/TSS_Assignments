package com.tss.model.inheritance.model;

public interface IPaymentMethod {
	void acceptPaymentDetails();
	void processPayment(double amount);
}