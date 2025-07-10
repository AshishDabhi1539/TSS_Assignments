package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.Base;
import com.tss.model.inheritance.model.Derived1;

public class BaseTest {

	public static void main(String[] args) {
		Base base;
		
		Derived1 derived1 = new Derived1();
		
		base=derived1;
		
		base.display();
	}

}
