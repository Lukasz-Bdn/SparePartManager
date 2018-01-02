package pl.lukasz.sparepartmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
	List<Shipment> findAllByIsArchivedAndOriginIsGlobal(boolean isArchived, boolean isGlobal);
}
