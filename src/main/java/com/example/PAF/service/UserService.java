package com.example.PAF.service;

import com.example.PAF.model.User;
import com.example.PAF.dtos.LoginRequest;
import com.example.PAF.dtos.RegisterRequest;
import com.example.PAF.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUserName())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUserName(registerRequest.getUserName());
        user.setHeadline(registerRequest.getHeadline());
        user.setPassword(registerRequest.getPassword());

        return userRepository.save(user);
    }

    public Optional<User> loginUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUserName(loginRequest.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (loginRequest.getPassword().equals(user.getPassword())) {
                return userOptional;
            }
        }
        return Optional.empty(); // Invalid credentials or user not found
    }

    public Optional<User> findByUsername(String username) {
        logger.info("Attempting to find user by username: '{}'", username); // Log the username being searched
        Optional<User> userOptional = userRepository.findByUserName(username);
        if (userOptional.isPresent()) {
            logger.info("User found: {}", username);
        } else {
            logger.warn("User not found: {}", username);
        }
        return userOptional;
    }

    public User findByUserName(String username) {
        Optional<User> user = findByUsername(username);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}