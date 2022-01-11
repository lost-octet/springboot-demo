package com.lostoctet.restservices.controllers;

import com.lostoctet.restservices.entities.Order;
import com.lostoctet.restservices.entities.User;
import com.lostoctet.restservices.exceptions.OrderNotFoundException;
import com.lostoctet.restservices.exceptions.UserNotFoundException;
import com.lostoctet.restservices.repositories.OrderRepository;
import com.lostoctet.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);

        if(!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found");

        return userOptional.get().getOrders();

    }

    //Method for Creating Order
    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(userid);
        if(!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found");

        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    //Method for Getting Order by ID
    @GetMapping("/{userid}/orders/{orderid}")
    public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException, OrderNotFoundException {

        Optional<User> userOptional = userRepository.findById(userid);
        Optional<Order> orderOptional = orderRepository.findById(orderid);
        if(!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found");
        else if(!orderOptional.isPresent())
            throw new OrderNotFoundException("Order Not Found");

        return orderOptional.get();
    }

}
