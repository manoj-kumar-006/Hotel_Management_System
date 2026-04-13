package com.mk.backend.controller;

import com.mk.backend.model.Room;
import com.mk.backend.model.Hotel;
import com.mk.backend.repository.RoomRepository;
import com.mk.backend.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/hotel/{id}")
    public List<Room> getRoomsByHotel(@PathVariable Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        return roomRepository.findByHotel(hotel);
    }
}