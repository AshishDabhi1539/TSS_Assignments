package com.tss.task;

public class Consumer implements Runnable {
    Q q;

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            q.get();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
            }
        }
    }
}