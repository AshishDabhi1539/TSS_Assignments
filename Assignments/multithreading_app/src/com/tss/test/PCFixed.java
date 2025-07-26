package com.tss.test;

import com.tss.task.Consumer;
import com.tss.task.Producer;
import com.tss.task.Q;

public class PCFixed {
    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }
}