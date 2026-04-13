package com.mk.backend.repository;

import com.mk.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByLocationIgnoreCase(String location); // ✅ ADD THIS
}