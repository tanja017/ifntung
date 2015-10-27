package net.codejava.springmvc.model;

import java.sql.Timestamp;

public class Wares {
	
	int wareId;
	String name;
	
	public Wares() {
	}
	
	public Wares(int waresId, String name) {
		this.wareId = waresId;
		this.name = name;
	}
	
	public int getWareId() {
		return wareId;
	}
	public void setWareId(int wareId) {
		this.wareId = wareId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	
}
