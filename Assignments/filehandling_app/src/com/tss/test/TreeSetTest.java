package com.tss.test;

import java.util.Set;
import java.util.TreeSet;

import com.tss.model.Employee;

public class TreeSetTest {

    public static void main(String[] args) {

        Set<Employee> set = new TreeSet<>();
        
        set.add(new Employee(1, "Ashish", 100000));
/*
        set.add(50);
        set.add(20);
        set.add(40);
        set.add(10);
        set.add(30);
        set.add(20);

        System.out.println("TreeSet Elements: " + set);

        System.out.println("First Element: " + ((TreeSet<Integer>) set).first());
        System.out.println("Last Element: " + ((TreeSet<Integer>) set).last());*/
    }
}
