package com.chill.questapp.controllers;

import com.chill.questapp.entities.User;
import com.chill.questapp.repos.UserRepository;
import com.chill.questapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")

public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }
    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getOneUser(userId);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId, newUser);
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
