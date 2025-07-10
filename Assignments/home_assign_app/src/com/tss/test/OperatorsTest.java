package com.tss.test;

import java.util.Scanner;

import com.tss.model.OperatorMenu;

public class OperatorsTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		OperatorMenu menu = new OperatorMenu();
		menu.display(scanner);
	}
}
