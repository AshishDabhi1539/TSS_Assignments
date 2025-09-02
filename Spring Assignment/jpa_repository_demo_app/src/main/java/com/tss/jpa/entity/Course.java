package com.tss.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	@Column
	private String courseName;
	@Column
	private int duration;
	@Column
	private double fees;
}
