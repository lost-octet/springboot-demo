package com.lostoctet.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserExistsException;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		//Check if user already exists
		User existingUser=userRepository.findByUsername(user.getUsername());
		if(existingUser != null)
			throw new UserExistsException("User Exists in DB");
		
		return userRepository.save(user);
	}
	
	//Get User By ID
	public Optional<User> getUserByID(Long id) throws  UserNotFoundException {
		Optional<User> user=userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("User Not Found in Repository");
			
		return user;
	}
	
	//Update user by ID
	public User updateUserByID(Long id, User user) throws UserNotFoundException {
		
		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("User not Present in DB");
		
		user.setUserid(id);
		return userRepository.save(user);
	}
	
	//Delete User By ID
	public void deleteUserByID(Long id) {
		
		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not Present in DB");
		
		userRepository.deleteById(id);
		
	}
	
	//Get user By User Name
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
