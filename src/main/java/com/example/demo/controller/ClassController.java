package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.contract.ResponseContract;
import com.example.demo.exception.CommonDomainException;
import com.example.demo.model.Classes;
import com.example.demo.service.ClassService;

@RestController
public class ClassController {
	@Autowired
	ClassService classService;
	
	@GetMapping("/class/{id}")
	public ResponseEntity<?> getClass(@PathVariable int id) throws CommonDomainException{
		return ResponseEntity.ok().body(classService.getClass(id));
		
	}
	@GetMapping("/class")
	public ResponseEntity<?> getAllClass(@RequestParam(name = "size") int limit, @RequestParam(name = "page") int offset){
		return ResponseEntity.ok().body(classService.getAllClass(limit, offset)) ;
		
	}
	
	@PostMapping("/create-class")
	public ResponseEntity<?> insert(@RequestBody Classes iclass) throws CommonDomainException{
		return ResponseEntity.ok().body(classService.insert(iclass));
	}
	
	@PutMapping("/update-class")
	public ResponseEntity<?> update(@RequestBody Classes iclass) throws CommonDomainException{
		return ResponseEntity.ok().body(classService.update(iclass));
	}
	
	@DeleteMapping("/delete-class/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws CommonDomainException{
		return ResponseEntity.ok().body(classService.delete(id));
	}
}
