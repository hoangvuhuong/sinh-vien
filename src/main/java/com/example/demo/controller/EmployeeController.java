package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/{id}/get-em")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
		return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public int update(@RequestBody Employee emp) {
		return employeeService.update(emp.getId(), emp.getName());
	}
	@DeleteMapping("/{id}/delete")
	public int delete(@PathVariable Integer id) {
		return employeeService.delete(id);
	}
	@PostMapping("/insert")
	public int delete(@RequestBody Employee emp) {
		return employeeService.insert(emp);
	}
}
