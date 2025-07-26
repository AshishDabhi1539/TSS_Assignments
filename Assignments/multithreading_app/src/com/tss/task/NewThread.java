package com.tss.task;

public class NewThread implements Runnable {
	String name;
    private Thread t;

    public NewThread(String threadname) {
        name = threadname;
        setT(new Thread(this, name));
        System.out.println("New thread: " + getT());
        getT().start();
    }

    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
        System.out.println(name + " exiting.");
    }

	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}

}
