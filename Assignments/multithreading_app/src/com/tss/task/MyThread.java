package com.tss.task;

public class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}

	public void run() {
		for (int i = 5; i > 0; i--) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread " + getName() + " was interrupted.");
			}
		}
	}
}