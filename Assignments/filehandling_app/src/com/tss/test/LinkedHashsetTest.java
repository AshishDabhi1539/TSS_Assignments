package com.tss.test;

import java.util.Set;
import java.util.LinkedHashSet;

public class LinkedHashsetTest {

    public static void main(String[] args) {

        Set<String> set = new LinkedHashSet<>();

        set.add("Apple");
        set.add("Banana");
        set.add("Orange");
        set.add("Mango");
        set.add("Banana");

        System.out.println("LinkedHashSet Elements: " + set);

        set.remove("Orange");
        System.out.println("After removing Orange: " + set);
        
        set.clear();
        System.out.println("After removing Orange: " + set);
    }
}
