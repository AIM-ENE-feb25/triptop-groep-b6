package com.prototype.triptop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TripTopConfigProvider {

    @Value("${booking.api.key}")
    private String bookingApiKey;

    public String getBookingApiKey() {
        return bookingApiKey;
    }
}
