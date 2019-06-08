package com.app.user.controller;

import com.app.user.dto.UserDto;
import com.app.user.mapper.UserMapper;
import com.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping(value = "createUser")
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
    }
}
