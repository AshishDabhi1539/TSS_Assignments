package com.tss.model.inheritance.model;

public interface IDemo {
	void method1();
	
	static void method2() {
		System.out.println("Implement in method2 with static method...");
	}
	
	default void method4() {
		method3();
	}
	
	private void method3() {
		System.out.println("Implement in method2 with static method...");
	}
}
