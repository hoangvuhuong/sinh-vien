package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.contract.ResponseContract;
import com.example.demo.model.Classes;
import com.example.demo.service.ClassService;

@RestController
public class ClassController {
	@Autowired
	ClassService classService;
	
	@GetMapping("/class/{id}")
	public ResponseContract<?> getClass(@PathVariable int id){
		return classService.getClass(id);
		
	}
	@GetMapping("/class")
	public ResponseContract<?> getAllClass(){
		return classService.getAllClass();
		
	}
	
	@PostMapping("/create-class")
	public ResponseContract<?> insert(@RequestBody Classes iclass){
		return classService.insert(iclass);
	}
	
	@PutMapping("/update-class")
	public ResponseContract<?> update(@RequestBody Classes iclass){
		return classService.update(iclass);
	}
	
	@DeleteMapping("/delete-class/{id}")
	public ResponseContract<?> delete(@PathVariable int id){
		return classService.delete(id);
	}
}
