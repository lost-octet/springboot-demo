package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserExistsException;
import com.lostoctet.restservices.exceptions.UserNameNotFoundException;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

//Controller
@RestController
@Validated
@RequestMapping(value = "/users")
@Tag(name = "Basic CRUD API", description = "Basic Operations on Users Repository")
public class UserController {

	//Autowire the service
	@Autowired
	private UserService userService;
	
	//getAllUsers Method and getMapping
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();	
	}
	
	//Method for Creating users using @RequestBody and @PostMapping
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			//UriComponentsBuilder builder;
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//Get User by ID, Using @Operation and @ApiResponse for OpenAPIv3 based Documentation
	@Operation(summary = "Get User By ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the User", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "Invalid ID Supplied", content = @Content)
	})
	@GetMapping("/{id}")
	public User getUserByID(@Parameter(name = "User ID", example = "12", required = true) @PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserByID(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	//Update user By ID
	@PutMapping("/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserByID(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//Delete User By ID
	@DeleteMapping("/{id}")
	public void deleteUserByID(@PathVariable Long id) {
		userService.deleteUserByID(id);
	}
	
	//Get User By Username
	@GetMapping("/byuser/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);

		if(user == null)
			throw new UserNameNotFoundException("Username " + username + " not Found in DB");

		return user;
	}
}
