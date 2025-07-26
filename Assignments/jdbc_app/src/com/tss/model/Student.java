package com.tss.model;

public class Student {
    private int studentId;
    private String studentName;
    private int age;
    private double percentage;
    private String rollNumber;

    public Student(int studentId, String studentName, int age, double percentage, String rollNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.percentage = percentage;
        this.rollNumber = rollNumber;
    }
    
    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getAge() {
        return age;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getRollNumber() {
        return rollNumber;
    }
}
