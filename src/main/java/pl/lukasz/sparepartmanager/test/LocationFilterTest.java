package pl.lukasz.sparepartmanager.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import pl.lukasz.sparepartmanager.entity.Location;
import pl.lukasz.sparepartmanager.entity.Shipment;
import pl.lukasz.sparepartmanager.entity.User;
import pl.lukasz.sparepartmanager.utility.LocationFilter;

public class LocationFilterTest extends TestCase {
	@Test
	public void testLocationFilter() {
		Location location1 = new Location();
		location1.setGlobal(true);
		Location location2 = new Location();
		location2.setGlobal(false);
		Shipment shipment1 = new Shipment();
		shipment1.setOrigin(location1);
		shipment1.setDestination(location1);
		Shipment shipment2 = new Shipment();
		shipment2.setOrigin(location2);
		shipment2.setDestination(location2);
		List<Shipment> shipments = new ArrayList<>();
		shipments.add(shipment1);
		shipments.add(shipment2);
		List<Shipment> expected = new ArrayList<>();
		expected.add(shipment1);
		User user1 = new User();
		User user2 = new User();
		user1.setLocation(location1);
		user1.setUserRole("ROLE_USER");
		user2.setLocation(location2);
		user2.setUserRole("ROLE_USER");
		assertTrue(LocationFilter.filterShipments(user1, shipments).equals(expected));
	}
}
