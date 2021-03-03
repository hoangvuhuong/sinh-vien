package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.contract.ResponseContract;
import com.example.demo.model.Classes;
import com.example.demo.repository.ClassRepository;

@Service
public class ClassService {
	@Autowired
	ClassRepository classRepository;
	
	public ResponseContract<?> getClass(int id){
		try {
			 return new ResponseContract<Classes>(HttpStatus.OK.toString(), "", classRepository.getClasses(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Classes>("", e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> getAllClass(){
		try {
			 return new ResponseContract<List<Classes>>(HttpStatus.OK.toString(), "", classRepository.getAllClass());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Classes>("", e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> insert(Classes iclass){
		try {
			return new ResponseContract<Integer>(HttpStatus.OK.toString(), "", classRepository.insert(iclass));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Integer>("", e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> update(Classes iclass){
		try {
			return new ResponseContract<Integer>(HttpStatus.OK.toString(), "", classRepository.update(iclass));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Integer>("", e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> delete(int id){
		try {
			return new ResponseContract<Integer>(HttpStatus.OK.toString(), "", classRepository.delete(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<Integer>("", e.getMessage(), -1);
		}
	}
}
