package com.mk.backend.service;

import com.mk.backend.model.*;
import com.mk.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private UserPreferencesRepository preferencesRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getRecommendations(User user) {

        UserPreferences pref = preferencesRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));

        // simple filter logic
        List<Room> rooms = roomRepository.findAll();

        return rooms.stream()
                .filter(r -> r.getPrice() <= pref.getBudget())
                .toList();
    }
}