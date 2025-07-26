package com.tss.test;

import com.tss.task.Caller;
import com.tss.task.Callme;

public class Synch {
    public static void main(String args[]) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");

        try {
            ob1.getT().join();
            ob2.getT().join();
            ob3.getT().join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}