package com.mk.backend.dto;

import lombok.Data;

@Data
public class RecommendationResponse {

    private Long roomId;
    private String hotelName;
    private double price;
}