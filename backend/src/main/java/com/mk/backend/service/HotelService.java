package com.mk.backend.service;

import com.mk.backend.model.Hotel;
import com.mk.backend.repository.HotelRepository;
import com.mk.backend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // ✅ Improved Search
    public List<Hotel> searchByLocation(String location) {

        List<Hotel> hotels = hotelRepository.findByLocationIgnoreCase(location);

        if (hotels.isEmpty()) {
            throw new ResourceNotFoundException("No hotels found in this location");
        }

        return hotels;
    }

    // ✅ Fixed Exception
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
    }
}