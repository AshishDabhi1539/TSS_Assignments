package com.tss.creational.builder_prototype.test;

import com.tss.creational.builder_prototype.model.Shape;

public class ShapeTest {

	public static void main(String[] args) {
        try {
            Shape originalShape = new Shape("Circle");
            Shape clonedShape = (Shape) originalShape.clone();

            System.out.println("Original Shape: " + originalShape.getType());
            System.out.println("Cloned Shape: " + clonedShape.getType());

            clonedShape.setType("Square");
            System.out.println("Modified Cloned Shape: " + clonedShape.getType());
            System.out.println("Original Shape remains: " + originalShape.getType());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
	}
}
