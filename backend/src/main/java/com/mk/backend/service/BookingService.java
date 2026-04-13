package com.mk.backend.service;

import com.mk.backend.model.*;
import com.mk.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.mk.backend.service.EmailService;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService; // ✅ EMAIL SERVICE ADDED

    // ✅ CREATE BOOKING
    public Booking createBooking(Long userId, Long roomId,
                                 LocalDateTime checkIn,
                                 LocalDateTime checkOut) {

        // ✅ Validate dates
        if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)) {
            throw new RuntimeException("Invalid date range");
        }

        // ✅ Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Get room
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // ✅ Check availability
        if (room.getAvailability() <= 0) {
            throw new RuntimeException("Room not available");
        }

        // ✅ Prevent overlapping bookings
        boolean isBooked = bookingRepository
                .existsByRoomAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
                        room, checkOut, checkIn
                );

        if (isBooked) {
            throw new RuntimeException("Room already booked for selected dates");
        }

        // ✅ Reduce availability
        room.setAvailability(room.getAvailability() - 1);
        roomRepository.save(room);

        // ✅ Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setStatus("BOOKED");

        Booking savedBooking = bookingRepository.save(booking);

        // ✅ 🔥 SEND EMAIL TO REGISTERED USER
        emailService.sendBookingEmail(
                user.getEmail(),                          // ✅ dynamic user email
                room.getHotel().getName(),
                room.getType(),
                checkIn.toString(),
                checkOut.toString()
        );

        return savedBooking;
    }

    // ✅ GET USER BOOKINGS
    public List<Booking> getUserBookings(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByUser(user);
    }

    // ✅ CANCEL BOOKING
    public void cancelBooking(Long bookingId, String reason) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        LocalDateTime now = LocalDateTime.now();

        // ✅ 12-hour rule
        if (now.isAfter(booking.getCheckIn().minusHours(12))) {
            throw new RuntimeException("Cannot cancel within 12 hours of check-in");
        }

        // ✅ Update booking
        booking.setStatus("CANCELLED");
        booking.setCancellationReason(reason);
        bookingRepository.save(booking);

        // ✅ Increase availability
        Room room = booking.getRoom();
        room.setAvailability(room.getAvailability() + 1);
        roomRepository.save(room);
    }
}