package com.prototype.triptop.service;

import com.prototype.triptop.client.ExternalApiClient;
import com.prototype.triptop.domain.Hotel;
import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import com.prototype.triptop.util.HotelResponseParser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalApiService {
    private final ExternalApiClient externalApiClient;
    private final HotelResponseParser hotelResponseParser;

    public ExternalApiService(ExternalApiClient externalApiClient, HotelResponseParser hotelResponseParser) {
        this.externalApiClient = externalApiClient;
        this.hotelResponseParser = hotelResponseParser;
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request, String apiKey) {
        String endpoint = "https://booking-com.p.rapidapi.com/v1/hotels/search";
        Map<String, String> params = new HashMap<>();
        params.put("locale", "en-us");
        params.put("dest_type", "city");
        params.put("filter_by_currency", "EUR");
        params.put("order_by", "popularity");
        params.put("units", "metric");
        params.put("dest_id", request.getDestinationId());
        params.put("checkin_date", request.getCheckInDate());
        params.put("checkout_date", request.getCheckOutDate());
        params.put("adults_number", String.valueOf(request.getAdults()));
        params.put("children_number", String.valueOf(request.getChildren()));
        params.put("room_number", String.valueOf(request.getRooms()));

        String response = externalApiClient.callApi(endpoint, params, apiKey);
        List<Hotel> hotels = hotelResponseParser.parseHotelsResponse(response);

        return new HotelSearchResponse(hotels);
    }
}
