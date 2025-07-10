package com.tss.test;

import com.tss.model.IAddition;

public class Addition {

	public static void main(String[] args) {
		IAddition object1 = (x, y) -> System.out.println(x + y);
		object1.sum(100, 20);
	}

}
