package com.david.demo.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            return userDTO;
        }).collect(Collectors.toList());
    }
}
