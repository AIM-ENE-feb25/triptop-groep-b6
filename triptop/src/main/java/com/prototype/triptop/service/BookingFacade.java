// BookingFacade.java
package com.prototype.triptop.service;

import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingFacade {
    private final BookingService bookingService;

    public BookingFacade(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request) {
        return bookingService.searchHotels(request);
    }
}