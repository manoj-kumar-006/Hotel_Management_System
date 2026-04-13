package com.mk.backend.controller;

import com.mk.backend.model.Hotel;
import com.mk.backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam String location) {
        return hotelService.searchByLocation(location);
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }
}