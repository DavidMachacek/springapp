package com.david.demo.user;

import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.demo.errorHandling.EmailExistsException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    Validator validator;

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
        logger.debug("New User registered as" + byEmail);
        return savedUser;
    }

    private boolean emailExist(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    public List<User> getAll() {
        return repository.findAll();
    }
}