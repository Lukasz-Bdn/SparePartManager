package pl.lukasz.sparepartmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	List<Location> findAllByIsGlobal(boolean bool);
}
