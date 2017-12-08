package com.zyd.entity;

import java.util.HashSet;
import java.util.Set;

public class Seat {
	private Integer Sid;
	private int row;
	private int column;
	private int priority;
	private int isFull;
	private Floor floor;
	private Set<Sit> sits ;
	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public Set<Sit> getSits() {
		return sits;
	}
	public void setSits(Set<Sit> sits) {
		this.sits = sits;
	}
	public Floor getFloor() {
		return floor;
	}
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	public int getIsFull() {
		return isFull;
	}
	public void setIsFull(int isFull) {
		this.isFull = isFull;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int i) {
		this.priority = i;
	}
}
 