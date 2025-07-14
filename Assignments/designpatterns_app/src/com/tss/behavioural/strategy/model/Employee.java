package com.tss.behavioural.strategy.model;

public class Employee {
	private int id;
    private String name;
    private IRole role;
    
	public Employee(int id, String name, IRole role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}
    
	public void promote(IRole newRole) {
        this.role = newRole;
    }

    public String getRoleDescription() {
        return role.roleDescription();
    }

    public String getResponsibility() {
        return role.getResponsibility();
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", role=" + role.roleDescription() + "]";
    }
}
