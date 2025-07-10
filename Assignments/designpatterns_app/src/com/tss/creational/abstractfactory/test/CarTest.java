package com.tss.creational.abstractfactory.test;

import com.tss.creational.abstractfactory.model.CarFactory;
import com.tss.creational.abstractfactory.model.CarType;
import com.tss.creational.abstractfactory.model.ICarFactory;
import com.tss.creational.abstractfactory.model.ICars;


public class CarTest {

	public static void main(String[] args) {
		
		ICarFactory marutiFactory = CarFactory.makeCars(CarType.Maruti);
        ICars maruti = marutiFactory.createCar();
        maruti.start();
        maruti.stop();
        
        System.out.println();

        ICarFactory tataFactory = CarFactory.makeCars(CarType.Tata);
        ICars tata = tataFactory.createCar();
        tata.start();
        tata.stop();
        
        System.out.println();

        ICarFactory mahindraFactory = CarFactory.makeCars(CarType.Mahindra);
        ICars mahindra = mahindraFactory.createCar();
        mahindra.start();
        mahindra.stop();
		
		/*ICars car = factory.makeCars(null);
		car.start();
		
		ICars car = factory.makeCars(CarType.Meruti);
		car.start();
		car.stop();*/
	}

}