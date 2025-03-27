package com.JournelApp.JournelApp;

import com.JournelApp.JournelApp.repositeries.UserRepository;
import com.JournelApp.JournelApp.services.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }



}
