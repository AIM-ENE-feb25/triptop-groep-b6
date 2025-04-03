package com.prototype.triptop.service;

import com.prototype.triptop.config.TripTopConfigProvider;
import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final String bookingApiKey;
    private final ExternalApiService externalApiService;

    public BookingService(ExternalApiService externalApiService, TripTopConfigProvider configProvider) {
        this.externalApiService = externalApiService;
        this.bookingApiKey = configProvider.getBookingApiKey();
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request) {
        return externalApiService.searchHotels(request, bookingApiKey);
    }
}
