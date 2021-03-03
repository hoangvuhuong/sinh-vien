package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;

public class Student {
	private int id;
	private String name;
	private String phone;
	@Email(message = "Email should be valid")
	private String email;
	@NotNull
	private int classId;
	private int identityCardId;
	@DateTimeFormat
	private Date birth;
	private Boolean sex;
	private IdentityCard identityCard;
	
	public IdentityCard getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(IdentityCard identityCard) {
		this.identityCard = identityCard;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getIdentityCardId() {
		return identityCardId;
	}
	public void setIdentityCardId(int identityCardId) {
		this.identityCardId = identityCardId;
	}
	public Date getStudentBirth() {
		return birth;
	}
	public void setStudentBirth(Date birth) {
		this.birth = birth;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
}
