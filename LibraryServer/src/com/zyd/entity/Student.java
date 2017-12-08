package com.zyd.entity;

import java.util.HashSet;
import java.util.Set;

public class Student {
	private Integer Id;
	private String StuentName;
	private String StudentId;
	private String institute;
	private String password;
	private Set<Sit> sits = new HashSet<Sit>();
	
	public Set<Sit> getSits() {
		return sits;
	}
	public void setSits(Set<Sit> sits) {
		this.sits = sits;
	}
	public Student() {
		
	}
	public Student(String StudentName, String StudentId, String institute, String password) {
		this.StuentName = StudentName;
		this.StudentId = StudentId;
		this.institute = institute;
		this.password = password;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getStuentName() {
		return StuentName;
	}
	public void setStuentName(String stuentName) {
		StuentName = stuentName;
	}
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
