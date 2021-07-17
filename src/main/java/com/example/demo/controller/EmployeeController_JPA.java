package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Employee;
import com.example.demo.jpa.EmployeeRespository;

@RestController
public class EmployeeController_JPA {
	@Autowired
	EmployeeRespository repo;

	@PostMapping("/employees-jpa")
	public ResponseEntity<Employee> addEmployee_jpa(@RequestBody Employee employee) {

		Employee emp = repo.save(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
	
	@GetMapping("/employees-jpa/{id}")
	public ResponseEntity<Optional<Employee>> findEmployee_jpa(@PathVariable int id) {

		Optional<Employee> emp = repo.findById(id);
		return new ResponseEntity<Optional<Employee>>(emp, HttpStatus.OK);
	}
	
	@GetMapping("/employees-jpa/name/{name}")
	public ResponseEntity<List<Employee>> findEmployee_repo(@PathVariable String name) {
		List<Employee> employees = repo.findByEmpname(name);
		if(employees.size()>0)
		{
			return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("employees-jpa/salary/{id}/{salary}")
	public ResponseEntity<Employee> updateEmployee_entity(@PathVariable long salary, @PathVariable int id) {
		int updated = repo.updateSalary(salary, id);
		if (updated > 0) {
			return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
