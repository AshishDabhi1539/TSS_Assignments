package com.tss.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.jpa.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
