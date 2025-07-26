package com.tss.task;

public class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
            }
        }
    }
}