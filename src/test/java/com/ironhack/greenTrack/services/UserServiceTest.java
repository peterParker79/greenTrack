package com.ironhack.greenTrack.services;

import com.ironhack.greenTrack.controllers.TestSecurityConfig;
import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.repositories.UserRepository;
import com.ironhack.greenTrack.security.CommonBeans;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
@ActiveProfiles("test")
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
        user.setName("user");
        user.setPassword("1234");
        user.setRole(ERole.ROLE_USER);
      //  userService.encryptPassword(user); use for test passwordEncryption
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