package pl.lukasz.sparepartmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SparePart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotEmpty
	@Size(min=4, max=80)
	private String serialNumber;
	@NotNull
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location currentLocation; //i.e location in the world
	private String currentStatus = "Available";
	private String currentStorageLocation; //i.e. shelf, box etc.
	
	@ManyToOne
	@JoinColumn(name="partCatalog_id")
	private PartCatalog partCatalog;
	
	public SparePart() {
		super();
	}
	
	public static List<SparePart> selectStatus(List<SparePart> loadedParts, String status) {
		List<SparePart> result = new ArrayList<>();
		for(SparePart sparePart : loadedParts) {
			if(sparePart.getCurrentStatus().equals(status)) {
				result.add(sparePart);
			}
		}
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCurrentStorageLocation() {
		return currentStorageLocation;
	}

	public void setCurrentStorageLocation(String currentStorageLocation) {
		this.currentStorageLocation = currentStorageLocation;
	}

}