package pl.lukasz.sparepartmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SparePart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;
	private String partNumber;
	private String serialNumber;
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location currentLocation; //i.e location in the world
	private int currentStatus;
	private String currentStorageLocation; //i.e. shelf, box etc.
	
	public SparePart() {
		super();
	}

	public SparePart(String name, Manufacturer manufacturer, String partNumber, 
						String serialNumber, Location currentLocation,
			int currentStatus, String currentStorageLocation) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
		this.partNumber = partNumber;
		this.serialNumber = serialNumber;
		this.currentLocation = currentLocation;
		this.currentStatus = currentStatus;
		this.currentStorageLocation = currentStorageLocation;
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

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
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

	public void setCurrentLocation(Location l) {
		this.currentLocation = currentLocation;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCurrentStorageLocation() {
		return currentStorageLocation;
	}

	public void setCurrentStorageLocation(String currentStorageLocation) {
		this.currentStorageLocation = currentStorageLocation;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}	
}