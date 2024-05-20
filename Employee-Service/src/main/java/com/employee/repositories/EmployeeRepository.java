package com.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	

}
