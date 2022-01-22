package com.lostoctet.restservices.dtos;

import lombok.Data;

@Data
public class UserMapStructDto {
    private long userid;
    private String username;
    private String emailAddress;
    private String roleName;
}
