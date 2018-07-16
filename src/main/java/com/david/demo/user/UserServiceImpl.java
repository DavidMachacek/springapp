package com.david.demo.user;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private UserDTO loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator, @Qualifier("loggedUser") UserDTO loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.loggedUser = loggedUser;
        setUp();
    }

    private void setUp() {
        userRepository.deleteAll();
        Role role = new Role();
        role.setName("ROLE_USER");

        UserDTO user = new UserDTO();
        user.setUsername("david");
        user.setPassword("david");
        user.setMatchingPassword("david");
        user.setFirstName("david");
        user.setLastName("david");
        user.setEmail("david@david.dd");
        user.setUserOrigin(UserOrigin.CUSTOM);
        user.setRole(Collections.singletonList(role));
        registerNewUserAccount(user);
    }

    @Override
    public UserDTO registerNewUserAccount(UserDTO accountDto) throws EmailExistsException, ConstraintException {

        Set<ConstraintViolation<UserDTO>> validationResult = validator.validate(accountDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintException(validationResult);
        }

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + accountDto.getEmail());
        }
        UserEntity newUserEntity = UserMapper.INSTANCE.userDTOToUser(accountDto);
        newUserEntity.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        UserEntity byEmail = userRepository.findByEmail(savedUserEntity.getEmail());
        logger.debug("New UserEntity registered as" + byEmail);
        return UserMapper.INSTANCE.userEntityToUserDTO(savedUserEntity);
    }

    private boolean emailExist(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getAll() {
        System.out.println("JAKO PRIHLASENY USER JE VEDEN " + loggedUser);
        List<UserEntity> userRepositoryAll = userRepository.findAll();
        return userRepositoryAll.stream().map(userEntity -> {
            UserDTO userDTO = mapper.userEntityToUserDTO(userEntity);
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public UserSocialDetails userDtoToUserSocial(UserDTO userDTO) {
        return UserSocialDetails.getBuilder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .userOrigin(userDTO.getUserOrigin())
                .build();
    }

    @Override
    public UserDTO loginUserAccount(String username, String password) {
        UserEntity byEmail = userRepository.findByUsername(username);
        if (username.equals(byEmail.getUsername()) && passwordEncoder.matches(password, byEmail.getPassword())) {
            return UserMapper.INSTANCE.userEntityToUserDTO(byEmail);
        }
        throw new UsernameNotFoundException("user not found");
    }

    @Override
    public UserDTO getCurrentLoggedUser() {
        return loggedUser;
    }
}