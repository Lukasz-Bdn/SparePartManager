package pl.lukasz.sparepartmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.lukasz.sparepartmanager.entity.Manufacturer;
import pl.lukasz.sparepartmanager.repository.ManufacturerRepository;

public class ManufacturerConverter implements Converter<String, Manufacturer> {
	@Autowired
	private ManufacturerRepository manufacturerRepo;

	@Override
	public Manufacturer convert(String source) {
		int id = Integer.parseInt(source);
		return manufacturerRepo.findOne(id);
	}
}