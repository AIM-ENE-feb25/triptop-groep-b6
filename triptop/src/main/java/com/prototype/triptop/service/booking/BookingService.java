package com.prototype.triptop.service.booking;

import com.prototype.triptop.config.TripTopConfigProvider;
import com.prototype.triptop.dto.HotelSearchRequestDTO;
import com.prototype.triptop.dto.HotelSearchResponseDTO;
import com.prototype.triptop.service.api.ExternalApiService;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final String bookingApiKey;
    private final ExternalApiService externalApiService;

    public BookingService(ExternalApiService externalApiService, TripTopConfigProvider configProvider) {
        this.externalApiService = externalApiService;
        this.bookingApiKey = configProvider.getBookingApiKey();
    }

    public HotelSearchResponseDTO searchHotels(HotelSearchRequestDTO request) {
        return externalApiService.searchHotels(request, bookingApiKey);
    }
}
