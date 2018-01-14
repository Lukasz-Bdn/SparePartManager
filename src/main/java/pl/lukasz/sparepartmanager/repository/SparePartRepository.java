package pl.lukasz.sparepartmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.SparePart;

public interface SparePartRepository extends JpaRepository<SparePart, Integer>{
	List<SparePart> findAllByCurrentLocationIsGlobal(boolean isGlobal);
	List<SparePart> findByCurrentStatus(String status);
	List<SparePart> findByCurrentLocationId(int id);
}