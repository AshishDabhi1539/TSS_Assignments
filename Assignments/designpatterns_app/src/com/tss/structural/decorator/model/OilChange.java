package com.tss.structural.decorator.model;

public class OilChange extends CarServiceDecorator {

	private static boolean oilAdded = false;
	
	public OilChange(ICarService carObject) {
		super(carObject);
	}
	
	@Override
	public double getCost() {
        if (!oilAdded) {
            oilAdded = true;
            return 500 + super.getCost();
        } else {
            return super.getCost();
        }
    }

}
