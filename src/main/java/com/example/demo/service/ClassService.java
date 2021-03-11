package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.contract.ResponseContract;
import com.example.demo.exception.CommonDomainException;
import com.example.demo.model.Classes;
import com.example.demo.repository.ClassRepository;

@Service
public class ClassService {
	@Autowired
	ClassRepository classRepository;
	
	public Classes getClass(int id) throws CommonDomainException{
		Classes iclass = classRepository.getClasses(id);
		if(iclass == null) {
			throw new CommonDomainException("Khong tim thay class");
		}
		return iclass;
	}
	
	public List<Classes> getAllClass(int limit, int offset){
			 return  classRepository.getAllClass(limit, offset);
	}
	
	public int insert(Classes iclass) throws CommonDomainException{
			int row = classRepository.insert(iclass);
			if(row == 0) {
				throw new CommonDomainException("insert that bai");
			}
			return row;
	}
	
	public int update(Classes iclass) throws CommonDomainException{
			int rows = classRepository.update(iclass);
			if(rows > 0) {
				return rows;
			}else {
				throw new CommonDomainException("Cap nhat that bai");
			}
	}
	
	public int delete(int id) throws CommonDomainException{
			int row = classRepository.delete(id);
			if(row == 0) {
				throw new CommonDomainException("delete that bai");
			}
			return row;
	}
}
