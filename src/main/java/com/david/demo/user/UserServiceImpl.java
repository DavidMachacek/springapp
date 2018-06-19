package com.david.demo.user;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.demo.errorHandling.ConstraintException;
import com.david.demo.errorHandling.EmailExistsException;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private final UserMapper mapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        setUp();
    }

    private void setUp() {
        userRepository.deleteAll();
        Role role = new Role();
        role.setName("ROLE_USER");

        UserDTO user = new UserDTO();
        user.setUsername("david");
        user.setPassword("david");
        user.setFirstName("david");
        user.setLastName("david");
        user.setEmail("david@david.dd");
        user.setRole(Collections.singletonList(role));
        registerNewUserAccount(user);
    }

    @Override
    public User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException, ConstraintException {

        Set<ConstraintViolation<UserDTO>> validationResult = validator.validate(accountDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintException(validationResult);
        }

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + accountDto.getEmail());
        }
        User newUser = UserMapper.INSTANCE.userDTOToUser(accountDto);
        newUser.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        User savedUser = userRepository.save(newUser);
        User byEmail = userRepository.findByEmail(savedUser.getEmail());
        logger.debug("New User registered as" + byEmail);
        return savedUser;
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}