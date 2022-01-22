package com.lostoctet.restservices.dtos;

import com.lostoctet.restservices.entities.Order;
import lombok.Data;
import java.util.List;

@Data
public class UserDtoV1 {

    private Long userid;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String ssn;
    private List<Order> orders;

}
