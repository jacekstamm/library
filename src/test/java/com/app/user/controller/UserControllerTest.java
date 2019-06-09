package com.app.user.controller;

import com.app.user.domain.User;
import com.app.user.dto.UserDto;
import com.app.user.exception.UserNotFoundException;
import com.app.user.mapper.UserMapper;
import com.app.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUsers() {
        //Given
        User testUser = new User("test name", "test surname");
        userService.saveUser(testUser);
        //When
        List<UserDto> result =  userController.getUsers();
        //Then
        assertEquals(1, result.size());
        //CleanUp
        userService.deleteUser(testUser.getId());
    }

    @Test
    public void getUser() throws UserNotFoundException {
        //Given
        User testUser = new User("test name", "test surname");
        userService.saveUser(testUser);
        //When
        UserDto result = userController.getUser(testUser.getId());
        //Then
        assertEquals("test name", result.getFirstName());
        //CleanUp
        userService.deleteUser(testUser.getId());
    }

    @Test
    public void createUser() {
        //Given
        User testUser = new User(1L, "test name", "test surname");
        //When
        try {
            userController.createUser(userMapper.mapToUserDto(testUser));
            //Then
            assertEquals("test name", userController.getUser(testUser.getId()).getFirstName());
            //CleanUp
            userService.deleteAll();
        } catch (UserNotFoundException ignored) {
        }
    }

    @Test
    public void updateUser() throws UserNotFoundException {
        //Given
        userController.createUser(new UserDto(1L, "Test name", "Test surname"));
        userController.getUser(1L).setFirstName("USERNAME TEST");
        //When
        UserDto result = userController.updateUser(userController.getUser(1L));
        //Then
        assertEquals("USERNAME TEST", result.getFirstName());
        //ClenUp
        userService.deleteUser(1L);
    }

    @Test
    public void deleteUser() {
        //Given

    }

    @Test
    public void borrowBook() {
    }
}