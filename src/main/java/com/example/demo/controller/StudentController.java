package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.contract.ResponseContract;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	@GetMapping("/country/students")
	public ResponseContract<?> getStudentsByCountry(@RequestParam String country) {
		return studentService.getByCountry(country);
	}
	
	@GetMapping("/class/{classId}/students")
	public ResponseContract<?> getByClassId(@PathVariable int classId){
		return studentService.getByClassId(classId);
	}
	
	@GetMapping("/student/infos/{id}")
	public ResponseContract<?> getById(@PathVariable int id){
		return studentService.getById(id);
	}
	
	@GetMapping("/student/{studentId}/list-class")
	public ResponseContract<?> getListClassByStudent(@PathVariable int studentId){
		return studentService.getListClassByStudent(studentId);
	}
	
	@PostMapping("/create-student")
	public ResponseContract<?> insert(@RequestBody Student student){
		return studentService.insert(student);
	}
	
	@DeleteMapping("/delete-student/{id}")
	public ResponseContract<?> delete(@PathVariable int id){
		return studentService.delete(id);
	}
}