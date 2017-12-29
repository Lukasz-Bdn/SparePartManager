package pl.lukasz.sparepartmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.lukasz.sparepartmanager.entity.Location;
import pl.lukasz.sparepartmanager.repository.LocationRepository;

public class LocationConverter implements Converter<String, Location> {
	@Autowired
	private LocationRepository locationRepo;

	@Override
	public Location convert(String source) {
		int id = Integer.parseInt(source);
		return locationRepo.findOne(id);
	}
}