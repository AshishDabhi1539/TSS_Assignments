package com.tss.jpa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.InstructorRequestDto;
import com.tss.jpa.dto.InstructorResponseDto;
import com.tss.jpa.entity.Instructor;
import com.tss.jpa.repository.InstructoreRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructoreRepository instructorRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public InstructorResponseDto addNewIntructor(InstructorRequestDto dto) {

		Instructor instructor = mapper.map(dto, Instructor.class);
		Instructor dbInstructor = instructorRepo.save(instructor);

		return mapper.map(dbInstructor, InstructorResponseDto.class);
	}

}
