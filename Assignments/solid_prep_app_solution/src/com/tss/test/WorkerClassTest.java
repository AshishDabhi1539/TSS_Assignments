package com.tss.test;

import com.tss.model.LabourClass;
import com.tss.model.RobotClass;

public class WorkerClassTest {

	public static void main(String[] args) {
		LabourClass labour = new LabourClass();
		labour.start();
		labour.eat();
		labour.rest();
		labour.stop();
		
		System.out.println();
		
		RobotClass robot = new RobotClass();
		robot.start();
		robot.stop();
	}

}
