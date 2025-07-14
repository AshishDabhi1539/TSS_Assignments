package com.tss.structural.proxy.test;

import com.tss.structural.proxy.model.Employee;
import com.tss.structural.proxy.model.EmployeeClass;
import com.tss.structural.proxy.model.EmployeeProxy;

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Employee empTableObj = new EmployeeProxy();
			
			empTableObj.create("ADMIN", new EmployeeClass());
			
			/*Employee empTableObj = new EmployeeProxy();

			EmployeeClass emp = new EmployeeClass(101, "Ashu");
			empTableObj.create("ADMIN", emp);
			empTableObj.delete("ADMIN", emp);*/
			
			System.out.println("Operation successful");
		}catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

}
