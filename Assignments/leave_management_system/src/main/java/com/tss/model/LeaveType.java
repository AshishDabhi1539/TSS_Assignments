package com.tss.model;

public class LeaveType {
	private int id;
	private String name;
	private int defaultDays;

	public LeaveType(int id, String name, int defaultDays) {
		this.id = id;
		this.name = name;
		this.defaultDays = defaultDays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDefaultDays() {
		return defaultDays;
	}

	public void setDefaultDays(int defaultDays) {
		this.defaultDays = defaultDays;
	}
}
