package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
	
}
