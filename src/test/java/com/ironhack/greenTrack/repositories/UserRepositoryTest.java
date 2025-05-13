package com.ironhack.greenTrack.repositories;

import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest// útil para integración
@Transactional // revierte la operación al final

class UserRepositoryTest {
    User user;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Daniel");
        user.setPassword("1234");
        user.setRole(ERole.ROLE_USER);
        user=userRepository.save(user);

        User user1 = new User();
        user1.setName("Felipe");
        user1.setPassword("1234");
        user1.setRole(ERole.ROLE_USER);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Cristina");
        user2.setPassword("5678");
        user2.setRole(ERole.ROLE_ADMIN);
        userRepository.save(user2);



    }


    @Test
    @DisplayName("Verify search User by id and integrity")
    void findById() {
        Optional<User> resultSeekBBDD=userRepository.findById(user.getId());
        assertTrue(resultSeekBBDD.isPresent(),"User found into BBDD");
        assertEquals(user.getName(),resultSeekBBDD.get().getName(),"Integrity name matched");
    }

    @Test
    void findByName() {
        Optional<User> resultSeekBBDD=userRepository.findByName(user.getName());
        assertTrue(resultSeekBBDD.isPresent(),"User found into BBDD");
        assertEquals(user.getName(),resultSeekBBDD.get().getName(),"Integrity name matched");
    }

    @Test
    void findAll() {
        List<User> users=userRepository.findAll();

        List<String> names = users.stream().map(User::getName).toList();
        assertTrue(names.contains("Daniel"), "User 'Daniel' should be present");
        assertTrue(names.contains("Felipe"), "User 'Felipe' should be present");
        assertTrue(names.contains("Cristina"), "User 'Cristina' should be present");
    }

    @Test
    void count() { //redundant incluyed below

        List<User> users=userRepository.findAll();
        assertEquals(3+3, users.size()); //BBDD contains previosly 3 users into.
    }
}