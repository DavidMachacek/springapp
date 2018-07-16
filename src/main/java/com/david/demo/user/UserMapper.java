package com.david.demo.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps transfer object and entity using mapstruct
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserEntity userDTOToUser(UserDTO user);

    UserDTO userDetailsToUserDTO(UserSocialDetails user);

    UserDTO userEntityToUserDTO(UserEntity user);

}
