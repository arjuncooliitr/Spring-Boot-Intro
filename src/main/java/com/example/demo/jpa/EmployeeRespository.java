package com.example.demo.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByEmpname(String empname);

	@Transactional
	@Modifying
	@Query("update com.example.demo.jpa.Employee set salary=:sal where id=:id")
	int updateSalary(@Param(value = "sal") long salary, @Param(value = "id") int id);
	
	

}
