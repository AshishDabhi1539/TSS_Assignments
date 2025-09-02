package com.tss.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors/add")
	public ResponseEntity<InstructorResponseDto> addNewInstructor(@RequestBody InstructorRequestDto dto)
	{
		return ResponseEntity.ok(instructorService.addNewIntructor(dto));
	}
}
