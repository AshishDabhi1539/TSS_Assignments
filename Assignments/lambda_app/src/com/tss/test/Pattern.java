package com.tss.test;

import com.tss.model.IPattern;

public class Pattern {

	public static void main(String[] args) {
		IPattern pattern = (raw) -> {
			int number = 1;

			for (int i = 1; i <= raw; i++) {
				for (int j = 1; j <= i; j++) {
					System.out.print(number + " ");
					number++;
				}
				System.out.println();
			}
		};

		pattern.pattern(3);
	}

}
