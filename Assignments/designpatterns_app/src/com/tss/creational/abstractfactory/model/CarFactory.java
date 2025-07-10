package com.tss.creational.abstractfactory.model;

public class CarFactory {
	
	public static ICarFactory makeCars(CarType car) {
		if(car==CarType.Maruti)
			return new MarutiFactory();
		
		if(car==CarType.Tata)
			return new TataFactory();
		
		if(car==CarType.Mahindra)
			return new MahindraFactory();
		
		return null;		
	}

}
