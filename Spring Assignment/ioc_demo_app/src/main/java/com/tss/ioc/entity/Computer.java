package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Computer {
	@Value("Apple")
	private String brand;
	@Autowired
	private Harddisk harddisk;
	
	public Computer() {
		super();
	}

	public Computer(String brand, Harddisk harddisk) {
		super();
		this.brand = brand;
		this.harddisk = harddisk;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Harddisk getHarddisk() {
		return harddisk;
	}

	public void setHarddisk(Harddisk harddisk) {
		this.harddisk = harddisk;
	}

	@Override
	public String toString() {
		return "Computer [brand=" + brand + ", harddisk=" + harddisk + "]";
	}
	
	

}
