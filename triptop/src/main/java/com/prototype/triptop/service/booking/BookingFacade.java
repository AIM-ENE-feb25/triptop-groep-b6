package com.prototype.triptop.service.booking;

import com.prototype.triptop.dto.HotelSearchRequestDTO;
import com.prototype.triptop.dto.HotelSearchResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class BookingFacade {
    private final BookingService bookingService;

    public BookingFacade(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public HotelSearchResponseDTO searchHotels(HotelSearchRequestDTO request) {
        return bookingService.searchHotels(request);
    }
}