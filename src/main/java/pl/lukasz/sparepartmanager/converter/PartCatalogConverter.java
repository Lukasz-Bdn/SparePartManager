package pl.lukasz.sparepartmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.lukasz.sparepartmanager.entity.PartCatalog;
import pl.lukasz.sparepartmanager.repository.PartCatalogRepository;

public class PartCatalogConverter implements Converter<String, PartCatalog> {
	@Autowired
	private PartCatalogRepository partCatalogRepo;

	@Override
	public PartCatalog convert(String source) {
		int id = Integer.parseInt(source);
		return partCatalogRepo.findOne(id);
	}
}