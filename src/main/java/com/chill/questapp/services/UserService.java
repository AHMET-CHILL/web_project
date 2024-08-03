package com.chill.questapp.services;

import com.chill.questapp.entities.User;
import com.chill.questapp.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {

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

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
