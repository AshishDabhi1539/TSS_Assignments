package com.tss.creational.builder_prototype.model;

public class Shape implements Cloneable {
	private String type;
	
	public Shape(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
