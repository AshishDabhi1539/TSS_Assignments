package com.tss.test;

import com.tss.model.Box;

public class BoxTest {

	public static void main(String[] args) {
		Box box1;
		box1 = new Box();
		
		Box box2;
		box2 = new Box();
		
		box1.display();
		box1.setHeight(50);
		box1.getHeight();
		box1.display();
		
		box2.display();
		box1.setWidth(100);
		box2.display();

	}

}
