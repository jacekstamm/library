package com.app.user.controller;

import com.app.user.dto.UserDto;
import com.app.user.exception.UserNotFoundException;
import com.app.user.mapper.UserMapper;
import com.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        LOGGER.info("Found List of books.");
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        LOGGER.info("User ID: " + userId + "found.");
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        LOGGER.info("New User created.");
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        LOGGER.info("USer information changed. User ID: " + userDto.getId());
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        LOGGER.info("User ID: " + userId + "deleted.");
        userService.deleteUser(userId);
    }
}
