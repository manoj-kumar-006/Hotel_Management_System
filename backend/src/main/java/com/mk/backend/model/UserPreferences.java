package com.mk.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferredLocation;
    private double budget;
    private String amenities;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}