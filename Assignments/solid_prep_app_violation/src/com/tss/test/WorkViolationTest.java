package com.tss.test;

import com.tss.mode.IWorker;
import com.tss.mode.Labour;
import com.tss.mode.Robot;

public class WorkViolationTest {
	public static void main(String[] args) {
		IWorker labour = new Labour();
		labour.startWork();
		labour.eat();
		labour.drink();
		labour.startWork();
		
		System.out.println();

		IWorker robot = new Robot();
		robot.startWork();
		robot.eat();
		robot.drink();
		robot.startWork();
	}
}
