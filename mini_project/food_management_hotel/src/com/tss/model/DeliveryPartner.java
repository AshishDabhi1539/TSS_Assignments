package com.tss.model;

import java.io.Serializable;

public class DeliveryPartner implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;

	public DeliveryPartner(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " (ID: " + id + ")";
	}

}
