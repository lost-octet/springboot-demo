package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.dtos.UserMapStructDto;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.mappers.UserMapper;
import com.lostoctet.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @GetMapping
    public List<UserMapStructDto> getAllUsers() {
        return userMapper.userstoUsersDto(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMapStructDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) throw new UserNotFoundException("User Not found. Call from DTO");

        User user = optionalUser.get();
        return userMapper.usertoUserDto(user);

    }

}
