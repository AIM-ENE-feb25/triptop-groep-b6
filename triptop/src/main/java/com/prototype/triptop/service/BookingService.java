package com.prototype.triptop.service;

import com.prototype.triptop.config.TripTopConfigProvider;
import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final ExternalApiService externalApiService;
    private final TripTopConfigProvider configProvider;

    public BookingService(ExternalApiService externalApiService, TripTopConfigProvider configProvider) {
        this.externalApiService = externalApiService;
        this.configProvider = configProvider;
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request) {
        String apiKey = configProvider.getBookingApiKey();
        return externalApiService.searchHotels(request, apiKey);
    }
}
