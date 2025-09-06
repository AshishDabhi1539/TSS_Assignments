package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Value;

public class Department {

    @Value("Computer Engineering")
    private String deptName;

    public Department() {
        super();
    }

    public Department(String deptName) {
        super();
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department [deptName=" + deptName + "]";
    }
}