package pl.lukasz.sparepartmanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Manufacturer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotEmpty
	@Size(min=3, max=55)
	private String name;
	@NotNull
	@NotEmpty
	@Size(min=5, max=200)
	private String returnAddress;
	private String additionalInfo;
	@OneToMany(mappedBy="manufacturer", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<PartCatalog> partCatalog;
	
	public Manufacturer() {
		super();
	}


	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", returnAddress=" + returnAddress + ", partCatalog="
				+ partCatalog + "]";
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

	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}

	public List<PartCatalog> getPartCatalog() {
		return partCatalog;
	}


	public void setPartCatalog(List<PartCatalog> partCatalog) {
		this.partCatalog = partCatalog;
	}


	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
}