package com.tss.model;

public class Student {
	private int rollNumber,age,sub1,sub2,sub3;
	private String name;
	
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setMarks(int sub1,int sub2, int sub3) {
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRollNumber() {
		return rollNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getSub1() {
		return sub1;
	}
	
	public int getSub2() {
		return sub2;
	}
	
	public int getSub3() {
		return sub3;
	}
	
	public float getAvgMarks() {		
		return (sub1+sub2+sub3)/3.0f;
	}
	
	public void display() {
		System.out.println();
		System.out.println("Roll Number : " + rollNumber);
		System.out.println("Name : " + name);
		System.out.println("Age : " + age);
		System.out.println("Subject1 Marks : " + sub1);
		System.out.println("Subject2 Marks : " + sub2);
		System.out.println("Subject3 Marks : " + sub3);
		System.out.println("Average : " + getAvgMarks());
	}
}
