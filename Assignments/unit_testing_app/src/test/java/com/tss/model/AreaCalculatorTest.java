package com.tss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AreaCalculatorTest {
	private AreaCalculator calc;

	@BeforeEach
	void init() {
		calc = new AreaCalculator();
	}

	@AfterAll
	static void display() {
		System.out.println("Testing done...");
	}

	@Test
	void testCircleArea() {
		assertEquals(314, calc.circleArea(10));
	}

	@Test
	void testRectangleArea() {
		assertEquals(300, calc.circleRectangle(30, 10));
	}
}
