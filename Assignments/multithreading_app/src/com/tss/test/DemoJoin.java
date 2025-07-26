package com.tss.test;

import com.tss.task.NewThread;

public class DemoJoin {

	public static void main(String[] args) {
		NewThread ob1 = new NewThread("One");
        NewThread ob2 = new NewThread("Two");
        NewThread ob3 = new NewThread("Three");

        System.out.println("Thread One is alive: " + ob1.getT().isAlive());
        System.out.println("Thread Two is alive: " + ob2.getT().isAlive());
        System.out.println("Thread Three is alive: " + ob3.getT().isAlive());

        try {
            System.out.println("Waiting for threads to finish.");
            ob1.getT().join();
            ob2.getT().join();
            ob3.getT().join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Thread One is alive: " + ob1.getT().isAlive());
        System.out.println("Thread Two is alive: " + ob2.getT().isAlive());
        System.out.println("Thread Three is alive: " + ob3.getT().isAlive());
        System.out.println("Main thread exiting.");
    }

}
