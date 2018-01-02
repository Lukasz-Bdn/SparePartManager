package pl.lukasz.sparepartmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String description;
	@NotNull
	@NotEmpty
	private String address;
	private boolean isGlobal; //true for global, false for remote locations
	@OneToMany(mappedBy="currentLocation", cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<SparePart> spareParts = new ArrayList<>();
 	
	public Location() {
		super();
	}

	public Location(String name, String description, String address, boolean isGlobal, List<SparePart> spareParts) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.isGlobal= isGlobal;
		this.spareParts = spareParts;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", description=" + description + ", address=" + address
				+ ", isGlobal=" + isGlobal + "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SparePart> getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(List<SparePart> spareParts) {
		this.spareParts = spareParts;
	}

	public boolean isGlobal() {
		return isGlobal;
	}
	
	public boolean getIsGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}
	
}