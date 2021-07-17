package com.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl_Template implements EmployeeDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		int rows = 0;
		String INSERT = "insert into myemp values(?,?,?,?)";
	
		rows=jdbcTemplate.update(INSERT, 
				employee.getEmpid(),
				employee.getEmpname(),employee.getAddress(),
				employee.getSalary());
		return rows;
	}
	
	@Override
	public boolean updateEmployee(int empId, String address) {
		// TODO Auto-generated method stub
		String UPDATE = "update myemp set address=? where empid=?";
		boolean updated = false;

		int rows = jdbcTemplate.update(UPDATE, address, empId);
		if (rows > 0) {
			return true;
		}
		return updated;
	}

	@Override
	public int deleteEmployee(int empid) {
		// TODO Auto-generated method stub
		String DELETE = "delete from myemp where empid=?";

		int rows = jdbcTemplate.update(DELETE, empid);

		return rows;
	}
	
	@Override
	public Employee findByEmpId(int empid) {
		// TODO Auto-generated method stub
		String FIND = "select * from myemp where empid=?";
		Employee employee = new Employee();
		try {
		employee = jdbcTemplate.queryForObject(FIND, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet set, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Employee e=new Employee();
				e.setEmpid(set.getInt(1));
				e.setEmpname(set.getString(2));
				e.setAddress(set.getString(3));
				e.setSalary(set.getLong(4));
				return e;
			}
		}, empid);
		} catch(EmptyResultDataAccessException e) {
			//logger
			System.out.println("No object found");
		}
		return employee;
	}
	@Override
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		String FIND = "select * from myemp ";
		List<Employee> employees = new ArrayList<>();
		employees = jdbcTemplate.query(FIND, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet set, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Employee e=new Employee();
				e.setEmpid(set.getInt(1));
				e.setEmpname(set.getString(2));
				e.setAddress(set.getString(3));
				e.setSalary(set.getLong(4));
				return e;
			}
		});
		return employees;
	}

}
