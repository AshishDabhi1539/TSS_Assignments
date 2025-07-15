package com.tss.model;

public class Account {
	private int id;
    private String name;
    private double salary;
    
	public Account(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Account{id=" + id + ", name='" + name + "', salary=" + salary + "}";
	}
    	
    
}
