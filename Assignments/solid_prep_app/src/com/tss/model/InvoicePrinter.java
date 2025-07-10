package com.tss.model;

public class InvoicePrinter {
	public void printHeader() {
	    System.out.println("ID\tDescription\tAmount\t\tTotal Tax(%)\tDiscount Percentage\tFinal Amount");
	    System.out.println("--\t-----------\t------\t\t------------\t-------------------\t------------");
	}
	
	public void printInvoice(Invoice invoice, InvoiceCalculator calculator) {
	    System.out.println(invoice.getId() + "\t" + invoice.getDescription() + "\t\t" + invoice.getCost()
	            + "\t\t" + calculator.getTaxPercent() + "\t\t" + invoice.getDiscountPercent()
	            + "\t\t\t" + calculator.calculateTotal(invoice));
	}

}
