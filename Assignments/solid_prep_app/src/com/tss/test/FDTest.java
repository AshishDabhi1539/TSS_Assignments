package com.tss.test;

import com.tss.model.FestivalType;
import com.tss.model.FixedDeposit;

public class FDTest {

	public static void main(String[] args) {
		FixedDeposit fd1 = new FixedDeposit(1, "Ashish", 40000, 6, FestivalType.NEWYEAR);
        FixedDeposit fd2 = new FixedDeposit(2, "Tushar", 50000, 4, FestivalType.DIWALI);
        FixedDeposit fd3 = new FixedDeposit(3, "Dishant", 30000, 5, FestivalType.HOLI);

        System.out.println(fd1);
        System.out.println("Simple Interest: " + fd1.calculateSimpleInterest());
        System.out.println();

        System.out.println(fd2);
        System.out.println("Simple Interest: " + fd2.calculateSimpleInterest());
        System.out.println();

        System.out.println(fd3);
        System.out.println("Simple Interest: " + fd3.calculateSimpleInterest());
	}

}
