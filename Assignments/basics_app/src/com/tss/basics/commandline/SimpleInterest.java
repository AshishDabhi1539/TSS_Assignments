package com.tss.basics.commandline;

public class SimpleInterest {

	public static void main(String[] args) {

		float principle = Float.parseFloat(args[0]);

		float rate = Float.parseFloat(args[1]);
		
		float time = Float.parseFloat(args[2]);
		
		float simpleInterest = (principle * rate * time) / 100;
		
		System.out.print("Simple Interest : " + simpleInterest);

	}

}
