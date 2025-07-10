package com.tss.test;

import com.tss.model.Demo2;
import com.tss.model.IDemo2;

public class Demo2Test {

	public static void main(String[] args) {

        test(Demo2::display);
        Demo2 demoObj = new Demo2();
        test(demoObj::show);
    }

    private static void test(IDemo2 demo) {
        demo.accept();
    }
}
