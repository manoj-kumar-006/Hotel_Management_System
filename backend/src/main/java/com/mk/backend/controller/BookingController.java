package com.mk.backend.controller;

import com.mk.backend.dto.BookingRequest;
import com.mk.backend.dto.CancelBookingRequest;
import com.mk.backend.model.Booking;
import com.mk.backend.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // ✅ Create Booking using DTO
    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {

        return bookingService.createBooking(
                request.getUserId(),
                request.getRoomId(),
                request.getCheckIn(),
                request.getCheckOut()
        );
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return bookingService.getUserBookings(userId);
    }

    // ✅ Cancel Booking using DTO
    @PutMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id,
                                @RequestBody CancelBookingRequest request) {

        bookingService.cancelBooking(id, request.getReason());

        return "Booking cancelled successfully";
    }
}