package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.dtos.UserDtoV1;
import com.lostoctet.restservices.dtos.UserDtoV2;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/versioning/param/users")
public class UserRequestParamVersioningController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //Get User by ID- Request Param Based Version-1
    @GetMapping(value ="/{id}", params = "version=1")
    public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        User user = userService.getUserByID(id);
        if(user == null) throw new UserNotFoundException("User Not Found Call versioning URI based v1.0");

        UserDtoV1 userDto = modelMapper.map(user, UserDtoV1.class);
        return userDto;
    }

    //Get User by ID- Request Param Based Version-2
    @GetMapping(value ="/{id}", params = "version=2")
    public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        User user = userService.getUserByID(id);
        if(user == null) throw new UserNotFoundException("User Not Found Call versioning URI based v2.0");

        UserDtoV2 userDto = modelMapper.map(user, UserDtoV2.class);
        return userDto;
    }
}
