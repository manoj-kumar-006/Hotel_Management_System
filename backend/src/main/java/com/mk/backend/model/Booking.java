package com.mk.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Date & Time
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    // ✅ Status (BOOKED / CANCELLED)
    private String status;

    // ✅ Optional cancellation reason
    private String cancellationReason;

    // ✅ Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ Relationship with Room
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}