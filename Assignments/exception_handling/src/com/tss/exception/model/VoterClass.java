package com.tss.exception.model;

import com.tss.exception.AgeNotValidException;

public class VoterClass {
	private String name;
	private int voterId, age;

	public VoterClass(String name, int voterId, int age) throws AgeNotValidException {
		super();
		this.name = name;
		this.voterId = voterId;
		if(age<18)
			throw new AgeNotValidException(age);
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws AgeNotValidException {
		if (age < 18)
			throw new AgeNotValidException(age);
		this.age = age;
	}

	@Override
	public String toString() {
		return "VoterClass [name=" + name + ", voterId=" + voterId + ", age=" + age + "]";
	}
}
