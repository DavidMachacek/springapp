package com.david.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional
    public User registerNewUserAccount(UserDTO accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + accountDto.getEmail());
        }
        User newUser = new User(accountDto.getFirstName(),
                accountDto.getLastName(),
                accountDto.getEmail());

        User savedUser = repository.save(newUser);
        User byEmail = repository.findByEmail(savedUser.getEmail());
        System.out.println("!!!!!!" + byEmail);
        return savedUser;
    }

    private boolean emailExist(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}