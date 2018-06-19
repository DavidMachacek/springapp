package com.david.demo.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.demo.errorHandling.EmailExistsException;

@Service
public interface UserService {
    User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;
    List<User> getAll();

}
