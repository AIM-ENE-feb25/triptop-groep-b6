package com.prototype.triptop.controller;

import com.prototype.triptop.dto.HotelSearchRequestDTO;
import com.prototype.triptop.dto.HotelSearchResponseDTO;
import com.prototype.triptop.service.booking.BookingFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingFacade bookingFacade;

    public BookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping("/hotels")
    public ResponseEntity<HotelSearchResponseDTO> searchHotels(
            @RequestParam String destinationId,
            @RequestParam int adults,
            @RequestParam int children,
            @RequestParam int rooms,
            @RequestParam String checkInDate,
            @RequestParam String checkOutDate) {

        HotelSearchRequestDTO request = new HotelSearchRequestDTO(destinationId, adults, children, rooms, checkInDate, checkOutDate);
        HotelSearchResponseDTO response = bookingFacade.searchHotels(request);
        return ResponseEntity.ok(response);
    }
}