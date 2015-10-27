package net.codejava.springmvc.model;

//import java.sql.Timestamp;

public class Warehouses {
	
	int whouseId;
	String name;
	
	public Warehouses() {
	}
	
	public Warehouses(int whouseId, String name) {
		this.whouseId = whouseId;
		this.name = name;
	}

	public int getWhouseId() {
		return whouseId;
	}

	public void setWhouseId(int WhouseId) {
		this.whouseId = WhouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}
	
	


	
	
}
