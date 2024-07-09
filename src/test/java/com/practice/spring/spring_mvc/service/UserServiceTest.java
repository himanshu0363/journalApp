package com.practice.spring.spring_mvc.service;

import com.practice.spring.spring_mvc.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void findByUsername() {
        assertNotNull(userRepository.findByUserName("aws"), "aws");
    }
}