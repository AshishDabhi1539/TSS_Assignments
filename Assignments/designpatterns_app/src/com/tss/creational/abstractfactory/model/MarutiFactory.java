package com.tss.creational.abstractfactory.model;

public class MarutiFactory implements ICarFactory {

	@Override
	public ICars createCar() {
		return new Maruti();
	}
	
}
