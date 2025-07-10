package com.tss.test;

import com.tss.mode.BirdClass;
import com.tss.mode.OstrichClass;
import com.tss.mode.PigeonClass;
import com.tss.mode.SparrowClass;

public class BirdTestClass {

	public static void main(String[] args) {
		BirdClass sparrow = new SparrowClass();
        BirdClass pigeon = new PigeonClass();
        BirdClass ostrich = new OstrichClass();

        sparrow.fly();
        pigeon.fly();
        ostrich.fly(); 
	}

}
