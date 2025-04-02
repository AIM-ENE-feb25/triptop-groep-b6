// BookingController.java
package com.prototype.triptop.controller;

import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import com.prototype.triptop.service.BookingFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingFacade bookingFacade;

    public BookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping("/hotels")
    public ResponseEntity<HotelSearchResponse> searchHotels(
            @RequestParam String destinationId,
            @RequestParam int adults,
            @RequestParam int children,
            @RequestParam int rooms,
            @RequestParam String checkInDate,
            @RequestParam String checkOutDate) {

        HotelSearchRequest request = new HotelSearchRequest(destinationId, adults, children, rooms, checkInDate, checkOutDate);
        HotelSearchResponse response = bookingFacade.searchHotels(request);
        return ResponseEntity.ok(response);
    }


}