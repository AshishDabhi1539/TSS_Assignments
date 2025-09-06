package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Employee {

    @Value("Ashish Dabhi")
    private String name;

    @Value("15000")
    private double salary;

    @Autowired
    private Department department;

    public Employee() {
        super();
    }

    public Employee(String name, double salary, Department department) {
        super();
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", salary=" + salary + ", department=" + department + "]";
    }
}