package com.tss.basics.commandline;

public class DistanceTwoPoints {

	public static void main(String[] args) {
		
		int X1 = Integer.parseInt(args[0]);
		int X2 = Integer.parseInt(args[1]);
		
		int Y1 = Integer.parseInt(args[2]);
		int Y2 = Integer.parseInt(args[3]);
		
		double distance = Math.sqrt(Math.pow(X2 - X1, 2) + Math.pow(Y2 - Y1, 2));
		System.out.println("\nDistance : " + distance);

	}

}
