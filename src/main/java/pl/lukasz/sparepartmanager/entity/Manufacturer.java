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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Manufacturer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String returnAddress;
	private String additionalInfo;
	@OneToMany(mappedBy="manufacturer", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<SparePart> spareParts;
	
	public Manufacturer() {
		super();
	}

	public Manufacturer(String name, String returnAddress, String additionalInfo, List<SparePart> spareParts) {
		super();
		this.name = name;
		this.returnAddress = returnAddress;
		this.additionalInfo = additionalInfo;
		this.spareParts = spareParts;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", returnAddress=" + returnAddress + ", spareParts="
				+ spareParts + "]";
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

	public List<SparePart> getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(List<SparePart> spareParts) {
		this.spareParts = spareParts;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
}