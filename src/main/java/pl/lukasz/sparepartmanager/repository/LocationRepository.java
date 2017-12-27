package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	
}
