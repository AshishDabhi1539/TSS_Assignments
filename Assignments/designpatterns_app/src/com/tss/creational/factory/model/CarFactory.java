package com.tss.creational.factory.model;

public class CarFactory {
	
	public ICars makeCars(CarType car) {
		if(car==CarType.Maruti)
			return new Maruti();
		
		if(car==CarType.Tata)
			return new Tata();
		
		if(car==CarType.Mahindra)
			return new Mahindra();
		
		return null;		
	}

}
