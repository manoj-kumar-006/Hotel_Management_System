package com.mk.backend.controller;

import com.mk.backend.model.Room;
import com.mk.backend.model.User;
import com.mk.backend.service.RecommendationService;
import com.mk.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public List<Room> getRecommendations(@PathVariable Long userId) {

        User user = userService.getUserById(userId);

        return recommendationService.getRecommendations(user);
    }
}