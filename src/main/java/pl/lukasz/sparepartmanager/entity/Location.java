package pl.lukasz.sparepartmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {
	@Id
	@GeneratedValue
	private int id;
	private String description;
	private String address;
	private boolean isGlobal; //true for global, false for remote locations
	
	public Location() {
		super();
	}

	public Location(String description, String address, boolean isGlobal) {
		super();
		this.description = description;
		this.address = address;
		this.isGlobal = isGlobal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}
}
