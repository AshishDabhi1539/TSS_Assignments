package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Value;

public class Harddisk {
	@Value("90")
	private int capacity;

	public Harddisk() {
		super();
	}

	public Harddisk(int capacity) {
		super();
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Harddisk [capacity=" + capacity + "]";
	}
	
	
}
