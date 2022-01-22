package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.dtos.UserModelMapperDto;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //Get User by ID
    @GetMapping("/{id}")
    public UserModelMapperDto getUserByID(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
            User user = userService.getUserByID(id);
            if(user == null) throw new UserNotFoundException("User Not Found Call from DTO");

        UserModelMapperDto userDto = modelMapper.map(user, UserModelMapperDto.class);
        return userDto;
    }

}
