package com.tss.exception.test;

import com.tss.exception.AgeNotValidException;
import com.tss.exception.model.VoterClass;

public class VoterClassTest {

	public static void main(String []args) {
		try {
			VoterClass voter = new VoterClass("Harshad",210,19);
			System.out.println(voter);
			
			VoterClass voter2 = new VoterClass("Mahek",310,10);
		}
		catch(AgeNotValidException e) {
			System.out.println(e);
		}
	}

}
