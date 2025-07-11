package com.tss.structural.facade.test;

import com.tss.structural.facade.model.HotelReception;

public class HotelTest {

	public static void main(String[] args) {
		HotelReception reception = new HotelReception();
		
		System.out.println("Fetching Indian Menu:");
		reception.getIndianMenu();
		
		
		System.out.println("\nFetching Italian Menu:");
		reception.getItalianMenu();
	}

}
