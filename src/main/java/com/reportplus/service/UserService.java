package com.reportplus.service;

import com.reportplus.model.User;
import com.reportplus.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

public void deleteUser(Long id) {

    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    userRepository.delete(user);

}

}