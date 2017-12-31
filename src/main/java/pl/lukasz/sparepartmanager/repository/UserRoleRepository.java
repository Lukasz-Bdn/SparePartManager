package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	UserRole findOneByUserId(int id);
	
}
