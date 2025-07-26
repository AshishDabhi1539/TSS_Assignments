package com.tss.test;

import com.tss.task.MyTask;
import com.tss.task.MyTask2;

public class ThreadTest {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new MyTask(), "Thread 1");
		thread1.start();
		Thread thread2 = new Thread(new MyTask(), "Thread 2");
		thread2.start();
		Thread thread5 = new Thread(new MyTask2(), "New Thread");
		thread5.start();
		Thread thread3 = new Thread(new MyTask(), "Thread 3");
		thread3.start();
		Thread thread4 = new Thread(new MyTask(), "Thread 4");
		thread4.start();
		Thread thread6 = new Thread(new MyTask2(), "New Thread2");
		thread6.start();
		
		Runnable task5 = () -> {
			System.out.println(Thread.currentThread().getName() + ": Task5 Completed.");
		};

		Thread thread7 = new Thread(task5, "Lambda Thread");
		thread7.start();
	}

}
