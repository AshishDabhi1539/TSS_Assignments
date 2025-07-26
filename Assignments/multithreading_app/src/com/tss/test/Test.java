package com.tss.test;

import com.tss.task.MyThread;

public class Test {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread());

		Thread.currentThread().setName("Current Thread");
		System.out.println(Thread.currentThread());

		Thread.currentThread().setPriority(9);
		System.out.println(Thread.currentThread());

		MyThread thread2 = new MyThread("High Priority Thread");
		thread2.start();
		thread2.setPriority(Thread.MAX_PRIORITY);

		MyThread thread3 = new MyThread("Low Priority Thread");
		thread3.start();
		thread3.setPriority(Thread.MIN_PRIORITY);

		MyThread thread1 = new MyThread("Thread 1");
		thread1.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Exiting");

	}

}
