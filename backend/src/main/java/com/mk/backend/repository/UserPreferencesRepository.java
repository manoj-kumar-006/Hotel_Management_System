package com.mk.backend.repository;

import com.mk.backend.model.UserPreferences;
import com.mk.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

    Optional<UserPreferences> findByUser(User user);
}