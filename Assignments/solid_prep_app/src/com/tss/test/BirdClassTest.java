package com.tss.test;

import com.tss.model.IBird;
import com.tss.model.IFlyable;
import com.tss.model.IWalkable;
import com.tss.model.OstrichClass;
import com.tss.model.PigeonClass;
import com.tss.model.SparrowClass;

public class BirdClassTest {

	public static void main(String[] args) {
        callBird(new SparrowClass());
        System.out.println();
        callBird(new PigeonClass());
        System.out.println();
        callBird(new OstrichClass());
    }

    static void callBird(IBird bird) {
        bird.getColor();

        if (bird instanceof IFlyable) {
            ((IFlyable) bird).fly();
        } else if (bird instanceof IWalkable) {
            ((IWalkable) bird).walk();
        }
    }

}
