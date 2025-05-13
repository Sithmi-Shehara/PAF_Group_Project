package com.example.PAF.service;

import com.example.PAF.model.User;
import com.example.PAF.dtos.LoginRequest;
import com.example.PAF.dtos.RegisterRequest;
import com.example.PAF.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

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
        return userRepository.findByUserName(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}