package com.tss.demo_spring_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello_Controller {
	
	@GetMapping("/sayHello")
	public String displayHello() {
		return "Hello Welcome to SpringBOOT";
	}
	
	@GetMapping("/sayBye")
	public String displayBye() {
		return "Bye! Bye!";
	}
}
