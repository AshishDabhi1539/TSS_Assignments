package com.tss.behavioural.strategy.test;

import com.tss.behavioural.strategy.model.Consultant;
import com.tss.behavioural.strategy.model.Employee;
import com.tss.behavioural.strategy.model.SeniorConsultant;
import com.tss.behavioural.strategy.model.TechLead;


public class StrategyTest {
	public static void main(String[] args) {
        Employee emp = new Employee(1, "Ashish", new Consultant());
        
        System.out.println("\nPromoted to: " + emp.getRoleDescription());
        System.out.println("Responsibility: " + emp.getResponsibility());
        System.out.println(emp);

        emp.promote(new SeniorConsultant());
        System.out.println("\nPromoted to: " + emp.getRoleDescription());
        System.out.println("Responsibility: " + emp.getResponsibility());
        System.out.println(emp);

        emp.promote(new TechLead());
        System.out.println("\nPromoted to: " + emp.getRoleDescription());
        System.out.println("Responsibility: " + emp.getResponsibility());
        System.out.println("\n" + emp);
    }
}
