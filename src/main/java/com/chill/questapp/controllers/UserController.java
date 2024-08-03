package com.chill.questapp.controllers;

import com.chill.questapp.entities.User;
import com.chill.questapp.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")

public class UserController {

    private UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser) {
        User exsistUser = userRepository.findById(userId).orElse(null);
        if (exsistUser != null) {
            exsistUser.setUserName(newUser.getUserName());
            exsistUser.setPassword(newUser.getPassword());
            return userRepository.save(exsistUser);

        }else {
            System.out.println("User not found");
            return null;
        }
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }
}
