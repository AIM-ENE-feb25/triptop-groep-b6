// BookingFacade.java
package com.prototype.triptop.service;

import com.prototype.triptop.domain.City;
import com.prototype.triptop.domain.CitySearchRequest;
import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingFacade {
    private final BookingService bookingService;

    public BookingFacade(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request) {
        return bookingService.searchHotels(request);
    }

//    public List<City> getCities(CitySearchRequest citySearchRequest) {
//        return bookingService.getCities(citySearchRequest);
//    }
}