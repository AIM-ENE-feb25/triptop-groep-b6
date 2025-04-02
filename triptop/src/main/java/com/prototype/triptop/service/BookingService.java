// BookingService.java
package com.prototype.triptop.service;

import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Value("${booking.api.key}")
    private String rapidApiKey;

    private final ExternalApiService externalApiService;

    public BookingService(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request) {
        return externalApiService.searchHotels(request, rapidApiKey);
    }
}