package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
	
}
