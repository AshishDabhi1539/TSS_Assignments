package com.tss.structural.proxy.model;

public class EmployeeProxy implements Employee {

	Employee employeeObj;
	
	public EmployeeProxy() {
		// TODO Auto-generated constructor stub
		employeeObj = new EmployeeImpl();
	}
	
	@Override
	public void create(String client, EmployeeClass obj) throws Exception {
		// TODO Auto-generated method stub
		if(client.equals("ADMIN")) {
			employeeObj.create(client, obj);
			return;
		}
		
		throw new Exception("Access Denied");
	}

	@Override
	public void delete(String client, int employeeId) throws Exception {
		// TODO Auto-generated method stub
		if(client.equals("ADMIN")) {
			employeeObj.delete(client, employeeId);
			return;
		}
		
		throw new Exception("Access Denied");
	}
	
	/* public void delete(String client, EmployeeClass obj) throws Exception {
	// TODO Auto-generated method stub
	if(client.equals("ADMIN")) {
		employeeObj.delete(client, obj);
		return;
	}
	
	throw new Exception("Access Denied");
}*/

	@Override
	public EmployeeClass get(String client, int employeeId) throws Exception {
		// TODO Auto-generated method stub
		if(client.equals("ADMIN") || client.equals("USER")) {
			return employeeObj.get(client, employeeId);
		}
		
		throw new Exception("Access Denied");
	}
	
}
