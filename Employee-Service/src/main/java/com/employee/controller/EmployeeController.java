package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entities.Employee;
import com.employee.entities.EmployeeResponse;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/employee/{id}")
	public EmployeeResponse getEmployeeById(@PathVariable("id") int id) {
		EmployeeResponse employee = service.getEmployeeDetails(id);
		return employee;
	}
}
