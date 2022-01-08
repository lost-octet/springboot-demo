package com.lostoctet.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.repositories.UserRepository;

//Service layer
@Service
public class UserService {
	
	//Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;
	
	//getAllUsers Method
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}
	
	//Method for Creating User
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	//Get User By ID
	public Optional<User> getUserByID(Long id) {
		Optional<User> user=userRepository.findById(id);
		return user;
	}
	
	//Put user by ID
	public User updateUserByID(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete User By ID
	public void deleteUserByID(Long id) {
		if(userRepository.existsById(id))
			userRepository.deleteById(id);
		
	}
	
	//Get user By User Name
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
