package com.tss.creational.abstractfactory.model;

public class TataFactory implements ICarFactory{

	@Override
	public ICars createCar() {
		return new Tata();
	}

}
