package com.tss.model;

public class Department {
	private String deptName;

	
    public Department() {
		super();
	}

	public Department(String deptName) {
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
