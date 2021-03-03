package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee getEmployee(int id) {
		Employee emp = new Employee();
		try {
			emp = employeeRepository.getEmployee(id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public int update(int id, String name) {
		try {
			return employeeRepository.update(id, name);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
	public int delete(int id) {
		try {
			return employeeRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
	public int insert(Employee emp) {
		try {
			return employeeRepository.insert(emp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
}
