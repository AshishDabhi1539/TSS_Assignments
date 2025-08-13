package com.tss.model;

public class LeaveBalance {
	private int empId;
	private int totalLeaves;
	private int leavesTaken;

	public LeaveBalance() {
	}

	public LeaveBalance(int empId, int totalLeaves, int leavesTaken) {
		this.empId = empId;
		this.totalLeaves = totalLeaves;
		this.leavesTaken = leavesTaken;
	}

	// Getters and setters

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getTotalLeaves() {
		return totalLeaves;
	}

	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}

	public int getLeavesTaken() {
		return leavesTaken;
	}

	public void setLeavesTaken(int leavesTaken) {
		this.leavesTaken = leavesTaken;
	}

	public int getRemainingLeaves() {
		return totalLeaves - leavesTaken;
	}
}
