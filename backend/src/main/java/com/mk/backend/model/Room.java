package com.mk.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double price;
    private int availability;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}