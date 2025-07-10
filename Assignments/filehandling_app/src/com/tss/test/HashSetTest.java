package com.tss.test;

import java.util.Set;
import java.util.HashSet;

public class HashSetTest {

    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();

        // Basic Methods
        set.add(20);
        set.add(10);
        set.add(30);
        set.add(99);
        set.add(10);

        System.out.println("HashSet Elements: " + set);

        System.out.println("Contains 30? " + set.contains(30));
        set.remove(99);
        System.out.println("After removing 99: " + set);

        System.out.println("Size: " + set.size());
        set.clear();
        System.out.println("After clearing: " + set.isEmpty());
    }
}
