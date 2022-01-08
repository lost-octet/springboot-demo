package com.lostoctet.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lostoctet.restservices.entities.User;

//Repository Layer
@Repository
public interface UserRepository extends JpaRepository <User, Long>{
	
	User findByUsername(String username);

}
