package pl.lukasz.sparepartmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.lukasz.sparepartmanager.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
	List<Shipment> findAllByIsArchivedAndOriginIsGlobal(boolean isArchived, boolean isGlobal);
	@Query("SELECT s FROM Shipment s ORDER BY dateShipped ASC")
	List<Shipment> findAllOrderByDateShipped();
		
}