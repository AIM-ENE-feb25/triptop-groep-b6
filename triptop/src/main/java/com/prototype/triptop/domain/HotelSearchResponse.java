package com.prototype.triptop.domain;

import java.util.List;

public class HotelSearchResponse {
        private List<Hotel> hotels;

        public HotelSearchResponse(List<Hotel> hotels) {
            this.hotels = hotels;
        }

        public HotelSearchResponse() {
            // Default constructor for parsing
        }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
