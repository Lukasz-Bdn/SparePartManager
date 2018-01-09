package pl.lukasz.sparepartmanager.utility;

import java.util.ArrayList;
import java.util.List;

import pl.lukasz.sparepartmanager.entity.Shipment;
import pl.lukasz.sparepartmanager.entity.SparePart;
import pl.lukasz.sparepartmanager.entity.User;

public class LocationFilter {
	
	public static List<Shipment> filterShipments(User sessionUser, List<Shipment> shipments) {
		if (sessionUser.getUserRole().equals("ROLE_ADMIN")) {
			return shipments;
		}
		List<Shipment> result = new ArrayList<>();
		for (Shipment shipment : shipments) {
			if(shipment.getDestination().getId()==sessionUser.getLocation().getId() ||
					shipment.getOrigin().getId()==sessionUser.getLocation().getId()) {
				result.add(shipment);
			}
		}
		return result;
	}
	
	public static List<SparePart> filterSparePart(User sessionUser, List<SparePart> spareParts) {
		if (sessionUser.getUserRole().equals("ROLE_ADMIN")) {
			return spareParts;
		}
		List<SparePart> result = new ArrayList<>();
		for (SparePart sparePart : spareParts) {
			if(sparePart.getCurrentLocation().getId()==sessionUser.getLocation().getId()) {
				result.add(sparePart);
			}
		}
		return result;
	}
	
}