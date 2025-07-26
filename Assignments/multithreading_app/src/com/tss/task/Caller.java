package com.tss.task;

public class Caller implements Runnable {
    String msg;
    Callme target;
    private Thread t;

    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        target.call(msg);
    }

    public Thread getT() {
        return t;
    }
}