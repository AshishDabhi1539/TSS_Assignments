package com.tss.structural.proxy.model;

public interface Employee {
	public void create(String client, EmployeeClass obj) throws Exception;
	public void delete(String client, int employeeId) throws Exception;
	// public void delete(String client, EmployeeClass obj) throws Exception;
	public EmployeeClass get(String client, int employeeId) throws Exception;
}
