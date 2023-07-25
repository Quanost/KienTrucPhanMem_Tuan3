package com.example.DemoCRUD_SpringBoot.controller;


import com.example.DemoCRUD_SpringBoot.model.User;
import com.example.DemoCRUD_SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {

        return userRepository.save(user);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {

        return ResponseEntity.ok(userRepository.findAll());
    }
}
