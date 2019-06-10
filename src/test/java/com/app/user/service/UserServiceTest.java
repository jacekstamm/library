package com.app.user.service;

import com.app.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void deleteAll() {
        //Given
        userService.saveUser(new User("John", "Snow"));
        userService.saveUser(new User("Jan", "Kowalski"));
        userService.saveUser(new User("John", "Travolta"));

        int beforeListSize = userService.getAllUsers().size();
        //When
        userService.deleteAll();
        int afterListSize = userService.getAllUsers().size();
        //Then
        assertEquals(3, beforeListSize);
        assertEquals(0, afterListSize);
    }
}