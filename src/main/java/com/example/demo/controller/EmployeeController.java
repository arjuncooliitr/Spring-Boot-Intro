package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.Employee;
import com.jdbc.EmployeeDAO;

@RestController
public class EmployeeController {
    @GetMapping("/employee1")
	public Employee getEmployee() {
		return new Employee(1,"Arjun",1,"Gwalior");
		
	}
    
    @GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return new Employee(id,"Arjun",1,"Gwalior");
		
	}
    
    @GetMapping("/employee/{id}/{name}")
	public Employee getEmployee(@PathVariable int id, @PathVariable("name") String n) {
		return new Employee(id,n,1,"Gwalior");
		
	}
    
    @PostMapping("/employee")
   	public Employee addEmployee(@RequestBody Employee emp) {
   		System.out.println("initial obj:" + emp);
   		emp.setEmpname(emp.getEmpname().toUpperCase());
   		return emp;
   		
   		
   	}
    
    @Autowired
    EmployeeDAO dao;
    
    @PostMapping("/employees-jdbc")
	public Employee addEmployee_jdbc(@RequestBody Employee employee) {

		int rows=dao.addEmployee(employee);
		if(rows>0)
		{
			return dao.findByEmpId(employee.getEmpid());
		}

		return new Employee();
	}
    
    
    @GetMapping("/employees-jdbc/{id}")
	public ResponseEntity<Employee> findEmployee_jdbc(@PathVariable int id) {

		Employee emp=dao.findByEmpId(id);
		if(emp.getEmpid()>0)
		{
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}

		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
    
    @DeleteMapping("/employees-jdbc/{id}")
	public ResponseEntity<Integer> deleteEmployee_jdbc(@PathVariable int id) {

		int rows=dao.deleteEmployee(id);
		if(rows>0)
		{
			return new ResponseEntity<Integer>(rows,HttpStatus.OK);
		}

		return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
	}
    
    @GetMapping("/employees-jdbc")
	public ResponseEntity<List<Employee>> getAllEmployee_jdbc() {

		List<Employee> emp=dao.findAllEmployees();
		if(emp.size()>0)
		{
			return new ResponseEntity<List<Employee>>(emp,HttpStatus.OK);
		}

		return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
	}
    
    @PutMapping("/employees-jdbc/{id}/{address}")
    public ResponseEntity<Boolean> updateEmployee_jdbc(@PathVariable int id,@PathVariable String address) {

		Boolean isUpdated = dao.updateEmployee(id, address);
		if(isUpdated)
		{
			return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
	}
    
}



