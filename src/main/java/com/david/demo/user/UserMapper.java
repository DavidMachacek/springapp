package com.david.demo.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.david.demo.customer.CustomerMapper;

/**
 * Maps transfer object and entity using mapstruct
 */
@Mapper
public interface UserMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    UserDTO userToUserDTO (User user);
    User userDTOToUser(UserDTO user);

}
