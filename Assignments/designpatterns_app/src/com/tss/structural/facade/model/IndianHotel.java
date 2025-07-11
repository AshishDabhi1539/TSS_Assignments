package com.tss.structural.facade.model;

public class IndianHotel implements IHotel{

	@Override
	public IMenu getMenu() {
		return new IndianMenu();
	}

}
