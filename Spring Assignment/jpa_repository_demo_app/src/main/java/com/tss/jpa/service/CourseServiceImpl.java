package com.tss.jpa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.dto.CourseRequestDto;
import com.tss.jpa.dto.CourseResponseDto;
import com.tss.jpa.entity.Course;
import com.tss.jpa.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CourseResponseDto addNewCourse(CourseRequestDto dto) {
		Course course = mapper.map(dto, Course.class);

		Course dbCourse = repo.save(course);

		return mapper.map(dbCourse, CourseResponseDto.class);
	}

}
