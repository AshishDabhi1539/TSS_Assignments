package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.DemoClass;
import com.tss.model.inheritance.model.IDemo;

public class DemoClassTest {

	public static void main(String[] args) {

		/*DemoClass demoClass = new DemoClass();

		demoClass.method1();

		IDemo.method2();

		demoClass.method4();*/

		IDemo demo1 = new IDemo() {
			
			@Override
			public void method1() {
				System.out.println("Demo1 Line");
			}
		};
		
		demo1.method1();
		
		
		Thread t = new Thread() {
			public void run() {
				System.out.println("Child Thread");
			}
		};

		/*Runnable r = new Runnable() {
			public void run() {
				System.out.println("Child Thread");
			}
		};
		
		Thread t = new Thread(r);*/

		t.start();
		System.out.println("Main Thread");
	}

}
