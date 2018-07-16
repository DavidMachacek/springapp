package com.david.demo.user;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return userService.getAll();
    }

    @PostMapping("/user")
    public void getUsers(@RequestBody UserDTO accountDto) {
        userService.registerNewUserAccount(accountDto);
    }
}
