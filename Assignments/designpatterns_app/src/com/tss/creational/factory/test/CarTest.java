package com.tss.creational.factory.test;

import com.tss.creational.factory.model.CarFactory;
import com.tss.creational.factory.model.CarType;
import com.tss.creational.factory.model.ICars;

public class CarTest {

	public static void main(String[] args) {
		CarFactory factory = new CarFactory();
		
		ICars car1 = factory.makeCars(CarType.Tata);
		car1.start();
		car1.stop();
		
		ICars car2 = factory.makeCars(CarType.Maruti);
		car2.start();
		car2.stop();
		
		ICars car3 = factory.makeCars(CarType.Mahindra);
		car3.start();
		car3.stop();
		
		/*ICars car = factory.makeCars(null);
		car.start();
		
		ICars car = factory.makeCars(CarType.Meruti);
		car.start();
		car.stop();*/
	}

}