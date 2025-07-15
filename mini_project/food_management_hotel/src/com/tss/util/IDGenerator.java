package com.tss.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
	private static final AtomicInteger counter = new AtomicInteger(1000);
	
	public static int generateId(){
		return counter.getAndIncrement();
	}
}
