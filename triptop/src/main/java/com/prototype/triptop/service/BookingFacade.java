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
        // Validate the request
        if (request.getDestinationId() == null || request.getDestinationId().isEmpty()) {
            throw new IllegalArgumentException("Destination ID cannot be null or empty");
        }
        if (request.getAdults() < 0 || request.getChildren() < 0 || request.getRooms() < 1) {
            throw new IllegalArgumentException("Invalid number of adults, children, or rooms");
        }

        // Call the booking service to perform the search
        return bookingService.searchHotels(request);
    }
}