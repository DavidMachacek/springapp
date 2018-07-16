package com.david.demo.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.demo.errorHandling.EmailExistsException;

@Service
public interface UserService {
    UserDTO registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;
    List<UserDTO> getAll();

    UserSocialDetails userDtoToUserSocial(UserDTO accountDto);

    UserDTO loginUserAccount(String username, String password);

    UserDTO getCurrentLoggedUser();
}
