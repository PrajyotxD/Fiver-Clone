package com.fiverr.service;

import com.fiverr.model.User;
import com.fiverr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // In a real application, you would add validation and password encoding here
        return userRepository.save(user);
    }
}
