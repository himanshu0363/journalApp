package com.practice.spring.spring_mvc.service;

import com.practice.spring.spring_mvc.entity.User;
import com.practice.spring.spring_mvc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ActiveProfiles("dev")
class UserDetailsServiceImplTest {

    @InjectMocks
    // creates an object for the below Class
    // and injects the objects for the class variables that are annotated as Mocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    // these will be injected
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // all Mocks will be initialized here.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().userName("aws").password("aws").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("aws");
        assertNotNull(userDetails, "aws");
    }
}