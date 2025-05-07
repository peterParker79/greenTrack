package com.ironhack.greenTrack.services;


import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserbyId(int id){
        User existingUserId = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return existingUserId;
    }

    public User getUserbyName(String name){
      //  User existingUserName = userRepository.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND))
        User existingUserName = userRepository.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return existingUserName;
    }
    public User createUser(String name, String password, ERole role){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);

        return user;
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
