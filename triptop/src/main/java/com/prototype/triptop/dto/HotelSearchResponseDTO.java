package com.prototype.triptop.dto;

import com.prototype.triptop.domain.Hotel;

import java.util.List;

public class HotelSearchResponseDTO {
    private List<Hotel> hotels;

    public HotelSearchResponseDTO(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
