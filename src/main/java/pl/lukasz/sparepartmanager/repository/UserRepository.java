package pl.lukasz.sparepartmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lukasz.sparepartmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findOneByUsername(String username);
	
}
