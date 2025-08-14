package com.tss.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeaveRequest {
	private int requestId;
	private int empId;
	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private String reason;
	private String rejectionReason;
	@SuppressWarnings("unused")
	private String startDateFormatted;
	@SuppressWarnings("unused")
	private String endDateFormatted;

	public LeaveRequest() {
	}

	public LeaveRequest(int requestId, int empId, String leaveType, LocalDate startDate, LocalDate endDate,
			String reason, String status, String rejectionReason) {
		this.requestId = requestId;
		this.empId = empId;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = status;
		this.rejectionReason = rejectionReason;
		this.startDateFormatted = startDate != null ? startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
		this.endDateFormatted = endDate != null ? endDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
	}

	// Getters and setters
	public String getStartDateFormatted() {
		return startDate != null ? startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
	}

	public void setStartDateFormatted(String startDateFormatted) {
		this.startDateFormatted = startDateFormatted;
	}

	public String getEndDateFormatted() {
		return endDate != null ? endDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
	}

	public void setEndDateFormatted(String endDateFormatted) {
		this.endDateFormatted = endDateFormatted;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
		this.startDateFormatted = startDate != null ? startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
		this.endDateFormatted = endDate != null ? endDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
}