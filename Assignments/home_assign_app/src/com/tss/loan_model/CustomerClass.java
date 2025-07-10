package com.tss.loan_model;

public class CustomerClass {
	private String name;
    private int age;
    private double income;
    private int creditScore;
    
	public CustomerClass(String name, int age, double income, int creditScore) {
		super();
		this.name = name;
		this.age = age;
		this.income = income;
		this.creditScore = creditScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	
	
}
