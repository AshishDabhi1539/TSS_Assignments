package com.tss.ioc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.ioc.entity.Computer;

@RestController
@RequestMapping("/app")
public class ComputerController {
	
	@Autowired
	private Computer computer;
	
	@GetMapping("/computer")
	public Computer getComputer() {
		return computer;
	}
	
	@GetMapping("/computer/name")
	public String getName() {
		return "Apple";
	}

}
