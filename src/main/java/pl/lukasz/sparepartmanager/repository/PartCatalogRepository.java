package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.PartCatalog;

public interface PartCatalogRepository extends JpaRepository<PartCatalog, Integer>{
}