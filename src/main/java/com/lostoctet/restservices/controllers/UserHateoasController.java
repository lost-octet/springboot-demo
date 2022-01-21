package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.entities.Order;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.repositories.UserRepository;
import com.lostoctet.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //Get all user
    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {
        List<User> allUsers = userService.getAllUsers();

        for(User user : allUsers) {
            //Self Linking all the users
            Long id = user.getUserid();
            Link selfLink = linkTo(this.getClass()).slash(id).withSelfRel();
            user.add(selfLink);

            //Relationship Link with getAllOrders.
            CollectionModel<Order> allOrders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(id);
            Link ordersLink = WebMvcLinkBuilder.linkTo(allOrders).withRel("All Orders");

            user.add(ordersLink);
        }
        //Self link for all users
        Link selfLinkAllUsers = linkTo(UserHateoasController.class).withSelfRel();
        return CollectionModel.of(allUsers, selfLinkAllUsers);
    }

    //Get User by ID
    @GetMapping("/{id}")
    public EntityModel<User> getUserByID(@PathVariable("id") @Min(1) Long id) {
        try {

            User user = userService.getUserByID(id);
            Long userid = user.getUserid();
            EntityModel<User> userEntityModel = EntityModel.of(user);
            Link selfLink = linkTo(UserHateoasController.class).slash(id).withSelfRel();
            userEntityModel.add(selfLink);
            return userEntityModel;
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}

