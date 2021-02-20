package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

	   public List<Employee> findAll();
	   
	   public Employee findById(int theID);
	   
	   public void save(Employee theEmployee);
	   
	   public void deleteId(int theId);
}
