package com.tss.model;

import java.util.Date;

public class LeaveRequest {
	private int id;
	private int employeeId;
	private int typeId;
	private String typeName;
	private Date startDate;
	private Date endDate;
	private Date appliedOn;
	private String status;
	private String reason;

	// Constructor for submitting a new leave (typeName not needed yet)
	public LeaveRequest(int id, int employeeId, int typeId, Date startDate, Date endDate, Date appliedOn, String status,
			String reason) {
		this.id = id;
		this.employeeId = employeeId;
		this.typeId = typeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.appliedOn = appliedOn;
		this.status = status;
		this.reason = reason;
	}

	// Constructor including typeName (used in history display)
	public LeaveRequest(int id, int employeeId, int typeId, String typeName, Date startDate, Date endDate,
			Date appliedOn, String status, String reason) {
		this(id, employeeId, typeId, startDate, endDate, appliedOn, status, reason);
		this.typeName = typeName;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
