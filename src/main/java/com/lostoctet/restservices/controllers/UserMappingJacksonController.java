package com.lostoctet.restservices.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lostoctet.restservices.config.FilterConfiguration;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(value = "/jackson/users")
@Validated
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FilterConfiguration filterConfig;

    //Get User by ID- Using Filter Configuration from the config package
//    @GetMapping("/{id}")
//    public User getUserByID(@PathVariable("id") @Min(1) Long id) {
//        try {
//            User user = userService.getUserByID(id);
//            ObjectMapper mapper=filterConfig.FilterConfiguration1();
//            ObjectWriter writer = mapper.writer();
//            String writeValueAsString = writer.writeValueAsString(user);
//            return mapper.readValue(writeValueAsString, User.class);
//
//        } catch (UserNotFoundException | JsonProcessingException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//        }
//    }

    //Get User by ID- Using Static Hashset
    @GetMapping("/{id}")
    public MappingJacksonValue getUserByID1(@PathVariable("id") @Min(1) Long id) {
        try {
            User user = userService.getUserByID(id);
            Set<String> fields = new HashSet<String>();
            fields.add("userid");
            fields.add("username");
            fields.add("ssn");
            fields.add("email");
            fields.add("orders");
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(true);
            simpleFilterProvider.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            mapper.setFilters(simpleFilterProvider);
            return mapper;

        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //Get User by ID- Using @RequestParam
    @GetMapping("/param/{id}")
    public MappingJacksonValue getUserByID2(@PathVariable("id") @Min(1) Long id, @RequestParam("fields") Set<String> fields) {
        try {
            User user = userService.getUserByID(id);
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(true);
            simpleFilterProvider.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            mapper.setFilters(simpleFilterProvider);
            return mapper;

        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
