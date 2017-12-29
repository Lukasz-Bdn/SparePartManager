package pl.lukasz.sparepartmanager.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private SparePart sparePart;
	@ManyToOne
	@JoinColumn(name="origin_id")
	private Location origin;
	@ManyToOne
	@JoinColumn(name="destination_id")
	private Location destination;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateShipped;
	private Date dateArrived;
	private String trackingInfo;
	
	public Shipment() {
		super();
	}

	public Shipment(SparePart sparePart, Location origin, Location destination, Date dateShipped, Date dateArrived,
			String trackingInfo) {
		super();
		this.sparePart = sparePart;
		this.origin = origin;
		this.destination = destination;
		this.dateShipped = dateShipped;
		this.dateArrived = dateArrived;
		this.trackingInfo = trackingInfo;
	}

	public SparePart getSparePart() {
		return sparePart;
	}

	public String getTrackingInfo() {
		return trackingInfo;
	}

	public void setTrackingInfo(String trackingInfo) {
		this.trackingInfo = trackingInfo;
	}

	public void setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public Date getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}

	public Date getDateArrived() {
		return dateArrived;
	}

	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
	}

	public Shipment updateWith(Shipment shipment) {
		if(shipment.getDestination()!=null) {
			this.destination = shipment.getDestination();
		}
		if (shipment.getDateShipped()!=null) {
			this.dateShipped = shipment.getDateShipped();
		}
		if (shipment.getTrackingInfo()!=null) {
			this.trackingInfo = shipment.getTrackingInfo();
		}
		return this;
	}
	
	public String getSimpleShippedDate() {
		if(this.dateShipped!=null) {
			return getSimpleDate(this.dateShipped);
		} else {
			return null;
		}
	}
	
	public String getSimpleArrivedDate() {
		if(this.dateArrived!=null) {
			return getSimpleDate(this.dateArrived);
		} else {
			return null;
		}
	}

	
	public String getSimpleDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		return day+"/"+month+"/"+year;
	}
}
