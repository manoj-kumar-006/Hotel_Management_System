package com.mk.backend.controller;

import com.mk.backend.dto.LoginRequest;
import com.mk.backend.dto.RegisterRequest;
import com.mk.backend.model.User;
import com.mk.backend.security.JwtUtil;
import com.mk.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register using DTO
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        return userService.register(user);
    }

    // ✅ Login using DTO
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User existingUser = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!existingUser.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(existingUser.getEmail());
    }
}