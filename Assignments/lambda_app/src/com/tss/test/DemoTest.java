package com.tss.test;

import com.tss.model.IDemo;

public class DemoTest {

	public static void main(String[] args) {
		IDemo object1 = () -> System.out.println("Learning FunctionalInterface In Java");

		object1.display();
	}

}
