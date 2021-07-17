package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@GetMapping("/messages")
	//@RequestMapping("/messages")
	public String getMessage() {
		return "Welcome to Spring Boot";
	}

}
