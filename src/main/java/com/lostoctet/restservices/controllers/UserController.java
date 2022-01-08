package com.lostoctet.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.services.UserService;

//Controller
@RestController
public class UserController {

	//Autowire the service
	@Autowired
	private UserService userService;
	
	//getAllUsers Method and getMapping
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();	
	}
	
	//Method for Creating users using @RequestBody and @PostMapping
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//Get User by ID 
	@GetMapping("/users/{id}")
	public Optional<User> getUserByID(@PathVariable("id") Long id) {
		return userService.getUserByID(id);
		
	}
	
	//Update user By ID
	@PutMapping("/update/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateUserByID(id, user);
	}
	
	//Delete User By ID
	@DeleteMapping("/delete/{id}")
	public void deleteUserByID(@PathVariable Long id) {
		userService.deleteUserByID(id);
	}
	
	//Get User By User Name
	@GetMapping("/users/byuser/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
}
