package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class IdentityCard {
	private int id;
	@NotNull
	private String code;
	@NotNull
	private String name;
	@NotNull
	private Date birth;
	@NotNull
	private String place;
	private int studentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
}