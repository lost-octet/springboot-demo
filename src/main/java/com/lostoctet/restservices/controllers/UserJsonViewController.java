package com.lostoctet.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.entities.Views;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.View;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {

    @Autowired
    private UserService userService;

    //Get User by ID- External
    @GetMapping("/external/{id}")
    @JsonView(Views.External.class)
    public User getUserByID(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserByID(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //Get User by ID- Internal
    @GetMapping("/internal/{id}")
    @JsonView(Views.Internal.class)
    public User getUserByID2(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserByID(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
