package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.entities.Order;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.repositories.OrderRepository;
import com.lostoctet.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);

        if(!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found");
        List<Order> allOrders = userOptional.get().getOrders();
        return CollectionModel.of(allOrders);
    }


}
