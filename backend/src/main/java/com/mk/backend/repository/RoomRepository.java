package com.mk.backend.repository;

import com.mk.backend.model.Room;
import com.mk.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel);
}