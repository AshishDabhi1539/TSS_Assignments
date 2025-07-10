package com.tss.model;

import java.util.Scanner;

public class RelationalOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		int x = 20, y = 5;
        System.out.println("\n--- Relational Operators ---");
        System.out.println("x : " + x + " & y : " + y);
        System.out.println("Equal to (x == y): " + (x == y));
        System.out.println("Not equal to (x != y): " + (x != y));
        System.out.println("Greater than (x > y): " + (x > y));
        System.out.println("Less than (x < y): " + (x < y));
	}

}
