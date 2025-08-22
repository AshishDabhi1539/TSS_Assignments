package com.tss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tss.model.Employee;

public class EmployeeTestXML {

	public static void main(String[] args) {
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("config2.xml");

        Employee emp = (Employee) context.getBean("employee");
        emp.showDetails();
    }

}
