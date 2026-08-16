package com.reportplus.controller;

import com.reportplus.model.User;
import com.reportplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

@DeleteMapping("/{id}")
public String deleteUser(@PathVariable Long id) {

    userService.deleteUser(id);

    return "User deleted successfully";
}

}