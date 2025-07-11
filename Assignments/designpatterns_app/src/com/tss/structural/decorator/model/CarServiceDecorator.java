package com.tss.structural.decorator.model;

public class CarServiceDecorator implements ICarService {

	private ICarService carObject;
	
	public CarServiceDecorator(ICarService carObject) {
		super();
		this.carObject = carObject;
	}

	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return carObject.getCost();
	}

}
