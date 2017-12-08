package com.zyd.entity;

import java.util.HashSet;
import java.util.Set;

public class Floor {
	private Integer Fid;
	private int priority;
	private Set<Seat> seats = new HashSet<Seat>();
	private int isFull;
	
	public int getIsFull() {
		return isFull;
	}
	public void setIsFull(int isFull) {
		this.isFull = isFull;
	}
	public Integer getFid() {
		return Fid;
	}
	public void setFid(Integer fid) {
		Fid = fid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Set<Seat> getSeats() {
		return seats;
	}
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	
}
