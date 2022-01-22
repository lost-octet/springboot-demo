package com.lostoctet.restservices.mappers;

import com.lostoctet.restservices.dtos.UserMapStructDto;
import com.lostoctet.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings(
            {
                    @Mapping(source = "email", target = "emailAddress"),
                    @Mapping(source = "role", target = "roleName")
            }
    )
    //User to UserMapStructDto Mapping DTO
    UserMapStructDto usertoUserDto(User user);

    //List<User> to List<UserMapStructDto> Mapping DTO
    List<UserMapStructDto> userstoUsersDto(List<User> users);

}
