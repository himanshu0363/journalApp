package com.practice.spring.spring_mvc.service;

import com.practice.spring.spring_mvc.entity.User;
import com.practice.spring.spring_mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void saveUserJournalEntries(User user) {
        userRepository.save(user);
    }


    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public void deleteByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepository.deleteByUserName(username);
    }

    public void updateUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userByUsername = findByUsername(username);
        userByUsername.setUserName(user.getUserName());
        userByUsername.setPassword(user.getPassword());
        saveUser(userByUsername);
    }

    private String getAuthenticatedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
