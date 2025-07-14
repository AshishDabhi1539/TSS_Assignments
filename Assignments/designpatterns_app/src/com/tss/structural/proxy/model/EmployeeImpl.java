package com.tss.structural.proxy.model;

public class EmployeeImpl implements Employee {

	@Override
	public void create(String client, EmployeeClass obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("created new row in the Employee table");
	}

	@Override
	public void delete(String client, int employeeId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("deleted row with employwwId : " + employeeId);
	}
	
	/*public void delete(String client, EmployeeClass obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Deleted employee with ID: " + obj.getId() + ", Name: " + obj.getName());
	}*/

	@Override
	public EmployeeClass get(String client, int employeeId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("fetching data from the DB");
		return new EmployeeClass();
	}

}
