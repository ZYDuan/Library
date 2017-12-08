package com.zyd.entity;

import java.util.Date;

public class Sit {
	private Integer SitId;
	private Seat seat;
	private Student student;
	private Date time;
	
	public Integer getSitId() {
		return SitId;
	}
	public void setSitId(Integer sitId) {
		SitId = sitId;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
