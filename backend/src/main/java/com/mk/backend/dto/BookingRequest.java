package com.mk.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {

    private Long userId;
    private Long roomId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}