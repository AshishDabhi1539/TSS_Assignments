package com.tss.model.inheritance.model;

public class DemoClass implements IDemo{

	@Override
	public void method1() {
		System.out.println("This is the method of DemoClass...");
	}
	
	static void method5() {
		System.out.println("Static Method of DemoClass...");
	}
	
	/*default void method2() {
		System.out.println("This is the default method of DemoClass...");
		method3();		
	}*/
	
	/*private void method3() {
		System.out.println("This is the private method of DemoClass...");
	}*/
}
