package com.david.demo.user;

import java.util.List;

import com.david.demo.errorHandling.EmailExistsException;

public interface UserService {
    User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;
    public List<User> getAll();

}
