package com.mk.backend.repository;

import com.mk.backend.model.Booking;
import com.mk.backend.model.User;
import com.mk.backend.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // ✅ Get bookings by user
    List<Booking> findByUser(User user);

    // ✅ 🔥 Check overlapping bookings (IMPORTANT)
    boolean existsByRoomAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
            Room room,
            LocalDateTime checkOut,
            LocalDateTime checkIn
    );
}