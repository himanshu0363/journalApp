package com.practice.spring.spring_mvc.controller;

import com.practice.spring.spring_mvc.entity.User;
import com.practice.spring.spring_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("health-check")
    public String healthCheck() {
        return "ok1";
    }

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            user.setRoles(List.of("user"));
            User createdUser = userService.saveUser(user);
            if (createdUser == null) {
                throw new Exception();
            }
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
