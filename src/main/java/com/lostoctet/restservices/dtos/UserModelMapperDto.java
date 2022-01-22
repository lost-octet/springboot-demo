package com.lostoctet.restservices.dtos;

import com.lostoctet.restservices.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserModelMapperDto {
    private long userid;
    private String username;
    private String firstname;
    private List<Order> orders;
}
