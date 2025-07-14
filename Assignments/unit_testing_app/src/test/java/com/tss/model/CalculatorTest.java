package com.tss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	private Calculator calc;
	
	@BeforeEach
	void init() {
		calc = new Calculator();
	}
	
	@AfterAll
	static void display() {
		System.out.println("Testing done...");
	}

	@Test
	void testAddition() {		
		assertEquals(5, calc.addition(3, 2));
		assertEquals(-10, calc.addition(-15, 5),"we can pass the error msg over here");
	}
	
	@Test
	void testSubstraction() {
		assertEquals(1, calc.substraction(3, 2));
		assertEquals(-20, calc.substraction(-15, 5));
	}
	
	@Test
	void testMultiplicatio() {
		assertEquals(6, calc.multiplication(3, 2));
		assertEquals(-10, calc.multiplication(-5, 2));
	}

	@Test
	void testDivision() {
	    assertEquals(5, calc.division(25, 5));
	    assertEquals(-10, calc.division(-20, 2));
	    //assertEquals(1, calc.division(10, 0));
	}

	@Test
	void testDivisionByZero() {
	    assertThrows(ArithmeticException.class, () -> calc.division(10, 0), "Division by zero should throw ArithmeticException");
	}

}
