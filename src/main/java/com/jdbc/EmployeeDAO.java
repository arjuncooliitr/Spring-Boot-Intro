package com.jdbc;

import java.util.List;

public interface EmployeeDAO {
	
	public int addEmployee(Employee employee);
	boolean updateEmployee(int empId,String address);
	int deleteEmployee(int empid);
	Employee findByEmpId(int empid);
	List<Employee>findAllEmployees();

}
