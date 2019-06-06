package com.app.library.user.controller;

import com.app.library.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/user")
public class UserController {

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(Long userId) {
        return new UserDto();
    }

    @PostMapping(value = "createUser")
    public void createUser() {

    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(Long userId) {
        return new UserDto();
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(Long userId) {

    }
}
