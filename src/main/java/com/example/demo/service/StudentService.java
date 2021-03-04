package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.contract.ResponseContract;
import com.example.demo.model.Classes;
import com.example.demo.model.IdentityCard;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentReporitory;

@Service
public class StudentService {
	@Autowired
	StudentReporitory studentRepository;
	final String PHONE_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	public final Pattern PHONE_VALIDATE = Pattern.compile(PHONE_REGEX, Pattern.CASE_INSENSITIVE);
	public final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
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
			Iterator<Student> iterator = studentRepository.getStudentByClassId(classId).iterator();
			List<Student> listStudents = new ArrayList<Student>();
			while(iterator.hasNext()) {
				Student student = iterator.next();
				IdentityCard idenCard = studentRepository.getIdentityCard(student.getIdentityCardId());
				student.setIdentityCard(idenCard);
				listStudents.add(student);
			}
			return new ResponseContract<List<Student>>(HttpStatus.OK.toString(), "", listStudents);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<List<Student>>(null, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> getListClassByStudent(int studentId){
		try {
			return new ResponseContract<List<Classes>>(HttpStatus.OK.toString(), "", studentRepository.getListClassByStudentId(studentId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<List<Student>>(null, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> insert(Student student){
		try {
			if(!isValid(student.getEmail()) || !isValidPhone(student.getPhone())) {
				return new ResponseContract<Integer>(null, "Email or phone should be valid", -1);
			}else {
				return new ResponseContract<Integer>(HttpStatus.OK.toString(), "", studentRepository.insert(student));
			}
			
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
	
	public ResponseContract<?> getByCountry(String country){
		try {
			return new ResponseContract<List<Student>>(HttpStatus.OK.toString(), "", studentRepository.getByCountry(country));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<List<Student>>(null, e.getMessage(), null);
		}
	}
	private  boolean isValid(final String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }
	private  boolean isValidPhone(final String phone) {
        Matcher matcher = PHONE_VALIDATE.matcher(phone);
        return matcher.matches();
    }
}
