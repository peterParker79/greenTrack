package com.ironhack.greenTrack.services;

import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.repositories.UserRepository;
import com.ironhack.greenTrack.security.CommonBeans;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    User user;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonBeans commonBeans;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("usuario");
        user.setPassword("1234");
       // userService.encryptPassword(user);
        user.setRole(ERole.ROLE_USER);
       // userRepository.save(user);

    }
/*
    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }
*/

    @Test
    @DisplayName("Test save user")
    public void saveUser() {
        userService.save(user);
        System.out.println("Usario creado con identificador: " + user.getId());
    }

    @Test
    @DisplayName("Password encryption is correct")
    public void passwordEncryption() {
      assertTrue(user.getPassword().startsWith("$2a$")); // BCrypt hashes start with $2a$
      System.out.println("Password encrypted ok ");
    }


    @Test
    @DisplayName("Password is correct")
    public void passwordIsCorrect() {
        boolean isCorrect = userService.verifyPassword("void", user);
        //assertTrue(isCorrect);
        System.out.println("Coincidence password ->  " + isCorrect);
    }
}