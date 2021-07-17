package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com")
public class SpringBootIntroductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntroductionApplication.class, args);
	}

}