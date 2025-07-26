package com.tss.task;

public class MyTask2 implements Runnable{

	@Override
	public void run() {
		for(int i = 3; i > 0; i--) {
			System.out.println("Implementing Threading Tasks");
		}
	}

}
