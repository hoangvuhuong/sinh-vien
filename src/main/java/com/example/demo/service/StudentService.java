package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.contract.ResponseContract;
import com.example.demo.model.Classes;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentReporitory;

@Service
public class StudentService {
	@Autowired
	StudentReporitory studentRepository;
	
	public ResponseContract<?> getById(int id) {
		try {
			return new ResponseContract<Student>(HttpStatus.OK.toString(), "", studentRepository.getStudentById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Student>(null, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> getByClassId(int classId) {
		try {
			return new ResponseContract<List<Student>>(HttpStatus.OK.toString(), "", studentRepository.getStudentByClassId(classId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<List<Student>>(null, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> getListClassByStudent(int studentId){
		try {
			return new ResponseContract<List<Classes>>(HttpStatus.OK.toString(), "", studentRepository.getListClassById(studentId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<List<Student>>(null, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> insert(Student student){
		try {
			return new ResponseContract<Integer>(HttpStatus.OK.toString(), "", studentRepository.insert(student));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Integer>(null, e.getMessage(), -1);
		}
	}
	
	public ResponseContract<?> delete(int id){
		try {
			return new ResponseContract<Integer>(HttpStatus.OK.toString(), "", studentRepository.delete(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Integer>(null, e.getMessage(), -1);
		}
	}
}
