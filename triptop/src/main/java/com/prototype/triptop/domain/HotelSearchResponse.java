package com.prototype.triptop.domain;

import java.util.List;

public class HotelSearchResponse {
    private List<Hotel> hotels;

    public HotelSearchResponse(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
