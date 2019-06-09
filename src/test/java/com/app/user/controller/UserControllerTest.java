package com.app.user.controller;

import com.app.user.domain.User;
import com.app.user.dto.UserDto;
import com.app.user.exception.UserNotFoundException;
import com.app.user.mapper.UserMapper;
import com.app.user.service.UserService;
import org.junit.After;
import org.junit.Before;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void before() {
        LOGGER.info("Start testing UserController Class");
    }

    @After
    public void after() {
        LOGGER.info("Finished testing UserController Class");
    }

    @Test
    public void testShouldGetUsers() {
        //Given
        userService.saveUser(new User("Jacek", "Stamm"));
        //When
        List<UserDto> result =  userController.getUsers();
        //Then
        assertEquals(1, result.size());
        //CleanUp
        userService.deleteUser(result.get(0).getId());
    }

    @Test
    public void testShouldGetUser() {
        //Given
        userService.saveUser(new User("Dorota", "Bocian"));
        Long userId = userController.getUsers().get(0).getId();
        //When
        try {
            UserDto result = userController.getUser(userId);
            //Then
            assertEquals(userId, result.getId());
            assertEquals("Bocian", result.getLastName());
            assertEquals("Dorota", result.getFirstName());
            //CleanUp
            userService.deleteUser(result.getId());
        } catch (UserNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldCreateUser() {
        //Given
        //When
        userController.createUser(userMapper.mapToUserDto(new User("John", "Snow")));
        Long userId = userController.getUsers().get(0).getId();
        //Then
        try {
            assertEquals("John", userController.getUser(userId).getFirstName());
            assertEquals("Snow", userController.getUser(userId).getLastName());
            //CleanUp
            userService.deleteUser(userId);
        } catch (UserNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldUpdateUser() {
        //Given
        userService.saveUser(new User("James", "Bond"));
        Long userId = userService.getAllUsers().get(0).getId();
        UserDto jamesKowalski = new UserDto();
        jamesKowalski.setId(userId);
        jamesKowalski.setLastName("Kowalski");
        //When
        userController.updateUser(jamesKowalski);
        //Then
        try {
            assertEquals("Kowalski", userController.getUser(userId).getLastName());
            //CleanUo
            userService.deleteUser(userId);
        } catch (UserNotFoundException ignored){
        }
    }

    @Test
    public void testShouldDeleteUser() {
        //Given
        userService.saveUser(new User("Jerzy", "Kiler"));
        Long userId = userService.getAllUsers().get(0).getId();
        int sizeBefore = userController.getUsers().size();
        //When
        userController.deleteUser(userId);
        int sizeAfter = userController.getUsers().size();
        //Then
        assertEquals(1, sizeBefore);
        assertEquals(0, sizeAfter);
    }
}