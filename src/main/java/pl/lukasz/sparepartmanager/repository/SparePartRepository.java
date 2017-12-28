package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.SparePart;

public interface SparePartRepository extends JpaRepository<SparePart, Integer>{
	
}
