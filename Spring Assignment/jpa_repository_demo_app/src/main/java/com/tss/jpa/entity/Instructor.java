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
@Table(name = "instructors")
public class Instructor {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long instructorId;
	
	@Column
	private String name;
	
	@Column
	private String qualification;
	
	@Column
	private int experience;
}
