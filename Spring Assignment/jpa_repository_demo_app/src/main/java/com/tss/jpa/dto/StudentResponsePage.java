package com.tss.jpa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponsePage {
	private List<StudentResponseDto> contents;
	private int totalElements;
	private int size;
	private boolean isLastPage;
	private int totalPages;
}
