package com.fiverr.service;

import com.fiverr.model.User;
import com.fiverr.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@test.com");
        user.setRole("BUYER");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertEquals("testuser", savedUser.getUsername());
        verify(userRepository).save(user);
    }
}
