package com.practice.spring.spring_mvc.controller;

import com.practice.spring.spring_mvc.entity.User;
import com.practice.spring.spring_mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            user.setRoles(List.of("admin", "user"));
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error - {}", user.getUserName(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
