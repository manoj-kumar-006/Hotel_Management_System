package com.mk.backend.security;

import com.mk.backend.model.User;
import com.mk.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}