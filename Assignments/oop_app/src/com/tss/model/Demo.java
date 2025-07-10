package com.tss.model;

public class Demo {
	private int a;
	private int b;
	private static int c;
	
	public Demo() {
		System.out.println("Inside pojo class constructor");
	}
	
	public void increment() {
		a++;
		b++;
		c++;
	}
	
	public void display() {
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("c : " + c);
	}
	
	public static void decrement() {
		// a--;
		// b--;
		
		Demo d1 = new Demo();
		d1.display();
		
		c--; //only static variable accept
	}
	
	static{
		System.out.println("Inside the pojo class with static");
	}
	
	{
		System.out.println("Inside the pojo class without any method");
	}
}
