package com.prototype.triptop.domain;

import java.util.List;

public class HotelSearchResponse {
    private List<Hotel> hotels;

    // Constructor to initialize hotels list
    public HotelSearchResponse(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Getter for hotels
    public List<Hotel> getHotels() {
        return hotels;
    }

    // Setter for hotels (optional)
    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
